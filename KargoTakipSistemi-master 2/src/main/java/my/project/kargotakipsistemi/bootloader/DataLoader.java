package my.project.kargotakipsistemi.bootloader;

import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.domain.Authority;
import my.project.kargotakipsistemi.domain.Customer;
import my.project.kargotakipsistemi.domain.Employee;
import my.project.kargotakipsistemi.domain.Packagestatus;
import my.project.kargotakipsistemi.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;
    private final PackagestatusRepository packagestatusRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final WarehouseRepository warehouseRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        saveRolesAndUsers();
        saveWarehousesAndLocations();
        savePackageStatuses();
    }

    private void savePackageStatuses() {

        packagestatusRepository.save(
                new Packagestatus(
                        "not assigned"
                )
        );
        packagestatusRepository.save(
                new Packagestatus(
                        "assigned"
                )
        );
        packagestatusRepository.save(
                new Packagestatus(
                        "completed"
                )
        );
        packagestatusRepository.save(
                new Packagestatus(
                        "cancelled"
                )
        );
    }

    private void saveWarehousesAndLocations() {
        var istanbul = new my.project.kargotakipsistemi.domain.Location(
                "Istanbul",
                "Turkey"
        );

        var ankara = new my.project.kargotakipsistemi.domain.Location(
                "Ankara",
                "Turkey"
        );

        var izmir = new my.project.kargotakipsistemi.domain.Location(
                "Izmir",
                "Turkey"
        );

        var antalya = new my.project.kargotakipsistemi.domain.Location(
                "Antalya",
                "Turkey"
        );

        var bursa = new my.project.kargotakipsistemi.domain.Location(
                "Bursa",
                "Turkey"
        );

        var adana = new my.project.kargotakipsistemi.domain.Location(
                "Adana",
                "Turkey"
        );

        var konya = new my.project.kargotakipsistemi.domain.Location(
                "Konya",
                "Turkey"
        );

        var gaziantep = new my.project.kargotakipsistemi.domain.Location(
                "Gaziantep",
                "Turkey"
        );

        var kayseri = new my.project.kargotakipsistemi.domain.Location(
                "Kayseri",
                "Turkey"
        );

        var mersin = new my.project.kargotakipsistemi.domain.Location(
                "Mersin",
                "Turkey"
        );

        var eskisehir = new my.project.kargotakipsistemi.domain.Location(
                "Eskisehir",
                "Turkey"
        );

        var diyarbakir = new my.project.kargotakipsistemi.domain.Location(
                "Diyarbakir",
                "Turkey"
        );

        var sanliurfa = new my.project.kargotakipsistemi.domain.Location(
                "Sanliurfa",
                "Turkey"
        );

        var malatya = new my.project.kargotakipsistemi.domain.Location(
                "Malatya",
                "Turkey"
        );

        var samsun = new my.project.kargotakipsistemi.domain.Location(
                "Samsun",
                "Turkey"
        );

        var kahramanmaras = new my.project.kargotakipsistemi.domain.Location(
                "Kahramanmaras",
                "Turkey"
        );

        var denizli = new my.project.kargotakipsistemi.domain.Location(
                "Denizli",
                "Turkey"
        );

        var erzurum = new my.project.kargotakipsistemi.domain.Location(
                "Erzurum",
                "Turkey"
        );

        var erzincan = new my.project.kargotakipsistemi.domain.Location(
                "Erzincan",
                "Turkey"
        );

        var tokat = new my.project.kargotakipsistemi.domain.Location(
                "Tokat",
                "Turkey"
        );

        var kars = new my.project.kargotakipsistemi.domain.Location(
                "Kars",
                "Turkey"
        );

        var kastamonu = new my.project.kargotakipsistemi.domain.Location(
                "Kastamonu",
                "Turkey"
        );

        istanbul = locationRepository.save(
                istanbul
        );

        ankara = locationRepository.save(
                ankara
        );

        izmir = locationRepository.save(
                izmir
        );

        antalya = locationRepository.save(
                antalya
        );

        bursa = locationRepository.save(
                bursa
        );

        adana = locationRepository.save(
                adana
        );

        konya = locationRepository.save(
                konya
        );

        gaziantep = locationRepository.save(
                gaziantep
        );

        kayseri = locationRepository.save(
                kayseri
        );

        mersin = locationRepository.save(
                mersin
        );

        eskisehir = locationRepository.save(
                eskisehir
        );

        diyarbakir = locationRepository.save(
                diyarbakir
        );

        sanliurfa = locationRepository.save(
                sanliurfa
        );

        malatya = locationRepository.save(
                malatya
        );

        samsun = locationRepository.save(
                samsun
        );

        kahramanmaras = locationRepository.save(
                kahramanmaras
        );

        denizli = locationRepository.save(
                denizli
        );

        erzurum = locationRepository.save(
                erzurum
        );

        erzincan = locationRepository.save(
                erzincan
        );

        tokat = locationRepository.save(
                tokat
        );

        kars = locationRepository.save(
                kars
        );

        kastamonu = locationRepository.save(
                kastamonu
        );

        var beykoz = new my.project.kargotakipsistemi.domain.Warehouse("Beykoz"
        );

        var cankaya = new my.project.kargotakipsistemi.domain.Warehouse(
                "Cankaya"
        );

        var bornova = new my.project.kargotakipsistemi.domain.Warehouse(
                "Bornova"
        );

        var muratpasa = new my.project.kargotakipsistemi.domain.Warehouse(
                "Muratpasa"
        );

        var osmangazi = new my.project.kargotakipsistemi.domain.Warehouse(
                "Osmangazi"
        );

        var seyhan = new my.project.kargotakipsistemi.domain.Warehouse(
                "Seyhan"
        );

        var selcuklu = new my.project.kargotakipsistemi.domain.Warehouse(
                "Selcuklu"
        );

        var sahitkamil = new my.project.kargotakipsistemi.domain.Warehouse(
                "Sahitkamil"
        );

        var melikgazi = new my.project.kargotakipsistemi.domain.Warehouse(
                "Melikgazi"
        );

        var tepebasi = new my.project.kargotakipsistemi.domain.Warehouse(
                "Tepebasi"
        );

        var baglar = new my.project.kargotakipsistemi.domain.Warehouse(
                "Baglar"
        );

        var haliliye = new my.project.kargotakipsistemi.domain.Warehouse(
                "Haliliye"
        );

        var yesilyurt = new my.project.kargotakipsistemi.domain.Warehouse(
                "Yesilyurt"
        );

        var ilicali = new my.project.kargotakipsistemi.domain.Warehouse(
                "Ilicali"
        );

        var atakum = new my.project.kargotakipsistemi.domain.Warehouse(
                "Atakum"
        );

        var oniki_eylul = new my.project.kargotakipsistemi.domain.Warehouse(
                "Oniki Eylul"
        );

        var andirin = new my.project.kargotakipsistemi.domain.Warehouse(
                "Andirin"
        );

        var merkez = new my.project.kargotakipsistemi.domain.Warehouse(
                "Merkez"
        );

        var merkezefendi = new my.project.kargotakipsistemi.domain.Warehouse(
                "Merkezefendi"
        );

        var palandoken = new my.project.kargotakipsistemi.domain.Warehouse(
                "Palandoken"
        );

        var refahiye = new my.project.kargotakipsistemi.domain.Warehouse(
                "Refahiye"
        );

        var odunpazari = new my.project.kargotakipsistemi.domain.Warehouse(
                "Odunpazari"
        );


        warehouseRepository.save(cankaya);
        warehouseRepository.save(beykoz);
        warehouseRepository.save(bornova);
        warehouseRepository.save(muratpasa);
        warehouseRepository.save(osmangazi);
        warehouseRepository.save(seyhan);
        warehouseRepository.save(selcuklu);
        warehouseRepository.save(sahitkamil);
        warehouseRepository.save(melikgazi);
        warehouseRepository.save(tepebasi);
        warehouseRepository.save(baglar);
        warehouseRepository.save(haliliye);
        warehouseRepository.save(yesilyurt);
        warehouseRepository.save(ilicali);
        warehouseRepository.save(atakum);
        warehouseRepository.save(oniki_eylul);
        warehouseRepository.save(andirin);
        warehouseRepository.save(merkez);
        warehouseRepository.save(merkezefendi);
        warehouseRepository.save(palandoken);
        warehouseRepository.save(refahiye);
        warehouseRepository.save(odunpazari);


        istanbul.getWarehousesByLocationId().add(beykoz);
        ankara.getWarehousesByLocationId().add(cankaya);
        izmir.getWarehousesByLocationId().add(bornova);
        antalya.getWarehousesByLocationId().add(muratpasa);
        bursa.getWarehousesByLocationId().add(osmangazi);
        adana.getWarehousesByLocationId().add(seyhan);
        konya.getWarehousesByLocationId().add(selcuklu);
        gaziantep.getWarehousesByLocationId().add(sahitkamil);
        kayseri.getWarehousesByLocationId().add(melikgazi);
        mersin.getWarehousesByLocationId().add(tepebasi);
        eskisehir.getWarehousesByLocationId().add(baglar);
        diyarbakir.getWarehousesByLocationId().add(haliliye);
        sanliurfa.getWarehousesByLocationId().add(yesilyurt);
        malatya.getWarehousesByLocationId().add(ilicali);
        samsun.getWarehousesByLocationId().add(atakum);
        kahramanmaras.getWarehousesByLocationId().add(oniki_eylul);
        denizli.getWarehousesByLocationId().add(andirin);
        erzurum.getWarehousesByLocationId().add(merkez);
        erzincan.getWarehousesByLocationId().add(merkezefendi);
        tokat.getWarehousesByLocationId().add(palandoken);
        kars.getWarehousesByLocationId().add(refahiye);
        eskisehir.getWarehousesByLocationId().add(odunpazari);


        beykoz.setLocationByLocationId(istanbul);
        cankaya.setLocationByLocationId(ankara);
        bornova.setLocationByLocationId(izmir);
        muratpasa.setLocationByLocationId(antalya);
        osmangazi.setLocationByLocationId(bursa);
        seyhan.setLocationByLocationId(adana);
        selcuklu.setLocationByLocationId(konya);
        sahitkamil.setLocationByLocationId(gaziantep);
        melikgazi.setLocationByLocationId(kayseri);
        tepebasi.setLocationByLocationId(mersin);
        baglar.setLocationByLocationId(eskisehir);
        haliliye.setLocationByLocationId(diyarbakir);
        yesilyurt.setLocationByLocationId(sanliurfa);
        ilicali.setLocationByLocationId(malatya);
        atakum.setLocationByLocationId(samsun);
        oniki_eylul.setLocationByLocationId(kahramanmaras);
        andirin.setLocationByLocationId(denizli);
        merkez.setLocationByLocationId(erzurum);
        merkezefendi.setLocationByLocationId(erzincan);
        palandoken.setLocationByLocationId(tokat);
        refahiye.setLocationByLocationId(kars);
        odunpazari.setLocationByLocationId(eskisehir);


        warehouseRepository.save(cankaya);
        warehouseRepository.save(beykoz);
        warehouseRepository.save(bornova);
        warehouseRepository.save(muratpasa);
        warehouseRepository.save(osmangazi);
        warehouseRepository.save(seyhan);
        warehouseRepository.save(selcuklu);
        warehouseRepository.save(sahitkamil);
        warehouseRepository.save(melikgazi);
        warehouseRepository.save(tepebasi);
        warehouseRepository.save(baglar);
        warehouseRepository.save(haliliye);
        warehouseRepository.save(yesilyurt);
        warehouseRepository.save(ilicali);
        warehouseRepository.save(atakum);
        warehouseRepository.save(oniki_eylul);
        warehouseRepository.save(andirin);
        warehouseRepository.save(merkez);
        warehouseRepository.save(merkezefendi);
        warehouseRepository.save(palandoken);
        warehouseRepository.save(refahiye);
        warehouseRepository.save(odunpazari);


        locationRepository.save(
                istanbul
        );
        locationRepository.save(
                ankara
        );
        locationRepository.save(
                izmir
        );
        locationRepository.save(
                antalya
        );
        locationRepository.save(
                bursa
        );
        locationRepository.save(
                adana
        );
        locationRepository.save(
                konya
        );
        locationRepository.save(
                gaziantep
        );
        locationRepository.save(
                kayseri
        );
        locationRepository.save(
                mersin
        );
        locationRepository.save(
                eskisehir
        );
        locationRepository.save(
                diyarbakir
        );
        locationRepository.save(
                sanliurfa
        );
        locationRepository.save(
                malatya
        );
        locationRepository.save(
                samsun
        );
        locationRepository.save(
                kahramanmaras
        );
        locationRepository.save(
                denizli
        );
        locationRepository.save(
                erzurum
        );
        locationRepository.save(
                erzincan
        );
        locationRepository.save(
                tokat
        );
        locationRepository.save(
                kars
        );
        locationRepository.save(
                kastamonu
        );

        warehouseRepository.save(cankaya);
        warehouseRepository.save(beykoz);
        warehouseRepository.save(bornova);
        warehouseRepository.save(muratpasa);
        warehouseRepository.save(osmangazi);
        warehouseRepository.save(seyhan);
        warehouseRepository.save(selcuklu);
        warehouseRepository.save(sahitkamil);
        warehouseRepository.save(melikgazi);
        warehouseRepository.save(tepebasi);
        warehouseRepository.save(baglar);
        warehouseRepository.save(haliliye);
        warehouseRepository.save(yesilyurt);
        warehouseRepository.save(ilicali);
        warehouseRepository.save(atakum);
        warehouseRepository.save(oniki_eylul);
        warehouseRepository.save(andirin);
        warehouseRepository.save(merkez);
        warehouseRepository.save(merkezefendi);
        warehouseRepository.save(palandoken);
        warehouseRepository.save(refahiye);
        warehouseRepository.save(odunpazari);

    }

    private void saveRolesAndUsers() {
        Authority customer = new Authority("ROLE_CUSTOMER");
        Authority employee = new Authority("ROLE_EMPLOYEE");
        Authority admin = new Authority("ROLE_ADMIN");

        authorityRepository.save(customer);
        authorityRepository.save(employee);
        authorityRepository.save(admin);

        var john = new Customer(
                "john",
                passwordEncoder.encode("sa"),
                "example@gmail.com",
                "05555555555"
        );

        var jane = new Employee(
                "jane",
                passwordEncoder.encode("sa"),
                "example@hotmail.com",
                "03333333333");

        var sane = new Employee(
                "sane",
                passwordEncoder.encode("sa"),
                "example@hotmail.com",
                "044444444444");

        customer.getUsers().add(john);
        employee.getUsers().add(jane);
        admin.getUsers().add(sane);

        john.getAuthorities().add(customer);
        jane.getAuthorities().add(employee);
        sane.getAuthorities().add(admin);

        customerRepository.save(john);
        employeeRepository.save(jane);
        employeeRepository.save(sane);

        authorityRepository.save(customer);
        authorityRepository.save(employee);
        authorityRepository.save(admin);
    }
}
