package my.project.kargotakipsistemi.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.dto.getRespDtos.ShipmentRespDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.EmployeeSaveReqDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.PackageReqDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.PackageReqDtoList;
import my.project.kargotakipsistemi.dto.saveReqDtos.ShipmentReqDto;
import my.project.kargotakipsistemi.dto.updateReqDtos.ShipmentEstimatedDeliveryDateReqDto;
import my.project.kargotakipsistemi.dto.updateReqDtos.ShipmentUpdateReqDto;
import my.project.kargotakipsistemi.repositories.CustomerRepository;
import my.project.kargotakipsistemi.repositories.EmployeeRepository;
import my.project.kargotakipsistemi.services.EmployeeService;
import my.project.kargotakipsistemi.services.LocationService;
import my.project.kargotakipsistemi.services.ShipmentService;
import my.project.kargotakipsistemi.services.WarehouseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shipment")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final LocationService locationService;
    private final WarehouseService warehouseService;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeService employeeService;

    /*
     *********************************************************************************************************************************************************
     *                                                                                                                                                       *
     *                                                                                                                                                       *
     *      The endpoints shown below are this controller are only accessible to users with the 'ROLE_CUSTOMER' role. They are managed by Customers.         *
     *                                                                                                                                                       *
     *                                                                                                                                                       *
     *********************************************************************************************************************************************************
     */


    // This endpoint saves a shipment to the database. It is only accessible to users with the 'ROLE_CUSTOMER' role.
    // Returns Save shipment page.
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("customer/save")
    public String saveShipment(Model model) {
        model.addAttribute("shipmentReqDto", new ShipmentReqDto());

        var fromLocations = locationService.getAllLocations();
        var toLocations = List.copyOf(fromLocations);

        model.addAttribute("fromLocations", fromLocations);
        model.addAttribute("toLocations", toLocations);

        return "save_shipment.html";
    }

    // Handles the form submission of the save shipment page.
    // Redirects to save shipment packages page.
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping(value = "customer/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveShipment(@ModelAttribute("shipmentReqDto") ShipmentReqDto shipmentReqDto, RedirectAttributes redirectAttributes) {

        var savedUri = shipmentService.saveShipment(shipmentReqDto);

        redirectAttributes.addAttribute("trackingNumber", savedUri);
        return "redirect:/shipment/customer/add-packages";
    }

    // This endpoint saves a package to the database. It is only accessible to users with the 'ROLE_CUSTOMER' role.
    // Returns Save package page.
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("customer/add-packages")
    public String packageForm(@RequestParam(required = false) String trackingNumber, Model model) {

        var packages = new PackageReqDto[5];


        // Model'e trackingNumber ve boş bir packageReqDto ekleyin
        model.addAttribute("trackingNumber", trackingNumber);
        model.addAttribute("packageReqDtos", packages);
        return "save_shipment_packages.html"; // package form sayfasının ismi
    }

    // Handles the form submission of the save package page.
    //    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("customer/add-packages/save")
    public String savePackagesToShipment(@RequestParam("trackingNumber") String trackingNumber, @ModelAttribute PackageReqDtoList packageReqDtoList, HttpSession session) {
        session.setAttribute("redirected", true);
        shipmentService.savePackagesToShipment(trackingNumber, packageReqDtoList.getPackageReqDtos().toArray(new PackageReqDto[0]));
        return "redirect:/shipment/customer/success";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("customer/tracking/{trackingNumber}")
    public String getShipmentByTrackingNumber(@PathVariable String trackingNumber, Model model) {
        var shipment = this.shipmentService.getShipment(trackingNumber);
        model.addAttribute("shipment", shipment);
        return "track-shipment.html";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("customer/home")
    public String getHomePage() {
        return "home.html";
    }


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("customer/success")
    public String getSuccessPage(HttpSession session) {
        Boolean redirected = (Boolean) session.getAttribute("redirected");
        if (redirected != null && redirected) {
            // Yönlendirme ile geldiyse özel işlemler yapılabilir
            session.removeAttribute("redirected"); // Attribute'u kaldır
            return "success.html"; // package form sayfasının ismi
        }
        return "redirect:/shipment/customer/error";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("customer/error")
    public String getErrorPage() {
        return "error.html"; // package form sayfasının ismi
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("customer/get-shipment-by-tracking-number")
    public ResponseEntity<ShipmentRespDto> getShipmentByTrackingNumber(@RequestParam String trackingNumber) {
        return ResponseEntity.ok(shipmentService.getShipment(trackingNumber));
    }

    /*
     *********************************************************************************************************************************************************
     *                                                                                                                                                       *
     *                                                                                                                                                       *
     *      The endpoints shown below are this controller are only accessible to users with the 'ROLE_EMPLOYEE' role. They are managed by Employees.         *
     *                                                                                                                                                       *
     *                                                                                                                                                       *
     *********************************************************************************************************************************************************
     */

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/management/shipments")
    public String getShipments(@RequestParam("packageStatusId") Optional<Integer> packageStatusId, @RequestParam("id") Optional<Integer> id, Model model) {
        if (packageStatusId.isPresent()) {
            model.addAttribute("shipments", shipmentService.getShipmentByPackageStatusId(packageStatusId.get()));
            return "edit-shipments.html";
        } else if (id.isPresent()) {
            model.addAttribute("shipments", shipmentService.getShipmentById(id.get()));
            return "edit-shipments.html";

        } else {
            model.addAttribute("shipments", shipmentService.getNotCompletedShipmentsByPackageStatusId(4));

            var fromWarehouses = warehouseService.findAll();
            var toWarehouses = List.copyOf(fromWarehouses);

            model.addAttribute("fromWarehouses", fromWarehouses);
            model.addAttribute("toWarehouses", toWarehouses);
            return "edit-shipments.html";
        }
    }

    // @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping(value = "management/edit-estimated-delivery-date",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveShipmentsDate(@RequestBody ShipmentEstimatedDeliveryDateReqDto shipmentEstimatedDeliveryDateReqDto) {

        this.shipmentService.saveShipmentEstimatedDeliveryDate(shipmentEstimatedDeliveryDateReqDto.getShipmentId(), shipmentEstimatedDeliveryDateReqDto.getEstimatedDelivery(), shipmentEstimatedDeliveryDateReqDto.getFromWarehouseId(), shipmentEstimatedDeliveryDateReqDto.getToWarehouseId(), shipmentEstimatedDeliveryDateReqDto.getDeliveredDate());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("management/get-shipments")
    public ResponseEntity<?> getNotAssignedShipments(@RequestParam("packageStatusId") Optional<Integer> packageStatusId, @RequestParam("id") Optional<Integer> id) {

        if (packageStatusId.isPresent()) {
            return ResponseEntity.ok(shipmentService.getShipmentByPackageStatusId(packageStatusId.get()));
        } else if (id.isPresent()) {
            return ResponseEntity.ok(shipmentService.getShipmentById(id.get()));
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PutMapping("management/update")
    public ResponseEntity<String> saveShipmentsDetails(@RequestBody ShipmentUpdateReqDto shipmentUpdateReqDto) {

        var savedUri = shipmentService.updateShipment(shipmentUpdateReqDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .queryParam("trackingNumber", savedUri)
                .buildAndExpand(savedUri)
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<>(savedUri, responseHeaders, HttpStatus.CREATED);
    }


    /*
     *********************************************************************************************************************************************************
     *                                                                                                                                                       *
     *                                                                                                                                                       *
     *      The endpoints shown below are this controller are only accessible to users with the 'ROLE_ADMIN' role. They are managed by Employees.         *
     *                                                                                                                                                       *
     *                                                                                                                                                       *
     *********************************************************************************************************************************************************
     */

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/users")
    public String getShipments(Model model) {

        var employees = employeeRepository.findAll();
        var customers = customerRepository.findAll();
        var admin = employees.stream().filter(employee -> employee.getAuthorities().stream().findFirst().get().getName().equals("ROLE_ADMIN")).findFirst().orElse(null);
        employees.remove(admin);

        model.addAttribute("shipments", shipmentService.getNotCompletedShipmentsByPackageStatusId(4));
        model.addAttribute("employees", employees);
        model.addAttribute("customers", customers);
        model.addAttribute("admin", admin);

        return "users.html";
    }


    @PostMapping("admin/add-emp")
    public String addEmp(@RequestBody EmployeeSaveReqDto employeeSaveReqDto) {

        employeeService.saveEmployee(employeeSaveReqDto);
        return "users.html";
    }
}
