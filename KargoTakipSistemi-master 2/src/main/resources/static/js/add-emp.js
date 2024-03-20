'use strict';

document.addEventListener('DOMContentLoaded', () => {
    // Employee ekleme formunu yakala
    const addBtn = document.querySelector('#add');

    // Formun submit olayını dinle
    addBtn.addEventListener('click', (event) => {
        event.preventDefault(); // Formun varsayılan gönderme işlemini engelle

        // Formdan verileri topla
        const employeeName = document.getElementById('employeeName').value;
        const employeeEmail = document.getElementById('employeeEmail').value;
        const password = document.getElementById('password').value;
        const csrfToken = document.getElementById('csrfToken').value;

        // Verileri JSON olarak hazırla
        const postData = {
            name: employeeName,
            email: employeeEmail,
            password: password
        };

        console.log(postData)

        // Fetch API ile POST isteği gönder
        fetch('http://localhost:8080/shipment/admin/add-emp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            credentials: 'include',
            body: JSON.stringify(postData)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Employee added successfully');
                    // Başarılıysa modalı kapat ve/veya sayfayı yenile
                    $('#addEmployeeModal').modal('hide');
                    window.location.reload();
                } else {
                    throw new Error('Failed to add employee');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});
