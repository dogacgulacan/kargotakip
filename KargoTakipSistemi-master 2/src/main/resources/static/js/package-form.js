'use strict';

window.addEventListener(
    'load',
    function () {
        let btnAll = document.querySelectorAll('#edit');

        btnAll.forEach(
            btn => {
                if (btn) {
                    btn.addEventListener('click', function (event) {
                        console.log(event)
                        let id = event.target.dataset.shipmentid;
                        let hiddenInput = document.getElementById('shipmentId');
                        hiddenInput.value = id;
                        console.log(id);
                    });
                }
                else {
                    console.log('no button');
                }
            }
        )
    }
)
function submitEditForm() {
    let shipmentId = document.querySelector('#shipmentId').value;
    let estimatedDelivery = document.querySelector('#estimatedDeliveryDate').value;
    let deliveredDate = document.querySelector('#deliveredDate').value;
    let fromLocationId = document.getElementById('fromLocationId').value;
    let toLocationId = document.getElementById('toLocationId').value;
    let csrfToken = document.getElementById('csrfToken').value;

    fetch('http://localhost:8080/shipment/management/edit-estimated-delivery-date', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        credentials: 'include',
        body: JSON.stringify({ shipment_id: shipmentId, estimated_delivery: estimatedDelivery, from_warehouse_id: fromLocationId, to_warehouse_id: toLocationId, delivered_date: deliveredDate })
    })
        .then(response => {
            if (response.ok) {
                console.log('Response ok');
                window.location.reload(); // Sayfayı yeniden yükle
            } else {
                throw new Error('Something went wrong');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}