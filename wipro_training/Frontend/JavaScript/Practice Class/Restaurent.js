$(document).ready(function () {
    const addForm = $("#addForm");
    const menuItemsList = $("#menuItems");
    const editModal = $("#editModal");
    const editItemName = $("#editItemName");
    const editItemPrice = $("#editItemPrice");
    const saveChangesBtn = $("#saveChangesBtn");
    const closeModalBtn = $("#closeModalBtn");
    const editCategory = $("#editCategory");
    const edititemDescription = $("#editDescription");
    let menuItems = [];
    let currentEditIndex = -1;
    // Add item to the menu 
    addForm.on("submit", function (e) {
        e.preventDefault();
        const itemName = $("#itemName").val();
        const itemPrice = $("#itemPrice").val();    
        const itemCategory = $("#category").val();
        const itemDescription = $("#itemDescription").val();

        if (itemName && itemPrice && itemCategory && itemDescription) {
            menuItems.push({ name: itemName, price: parseFloat(itemPrice), category: itemCategory, description: itemDescription });
            displayMenuItems();
            addForm[0].reset();
        }
    });

    // Display menu items in the list
    function displayMenuItems() {
        menuItemsList.empty();
        menuItems.forEach((item, index) => {
            const li = $("<li></li>").html(`${item.name} - $${item.price.toFixed(2)} - ${item.category}, ${item.description} 
            <button class="btn btn-primary btn-sm" onclick="editItem(${index})"> Edit</button>
            <button class="btn btn-danger btn-sm" onclick="deleteItem(${index})"> Delete</button>`);
            menuItemsList.append(li);
        });
    }
});














// document.addEventListener("DOMContentLoaded", function () {
//     const addForm = document.getElementById("addForm");
//     const menuItemsList = document.getElementById("menuItems");
//     const editModal = document.getElementById("editModal");
//     const editItemName = document.getElementById("editItemName");
//     const editItemPrice = document.getElementById("editItemPrice");
//     const saveChangesBtn = document.getElementById("saveChangesBtn");
//     const closeModalBtn = document.getElementById("closeModalBtn");
//     const editCategory = document.getElementById("editCategory");
//     const edititemDescription = document.getElementById("editDescription")


//     let menuItems = [];
//     let currentEditIndex = -1;

//     // Add item to the menu
//     addForm.addEventListener("submit", function (e) {
//         e.preventDefault();
//         const itemName = document.getElementById("itemName").value;
//         const itemPrice = document.getElementById("itemPrice").value;
//         const itemCategory = document.getElementById("category").value;
//         const itemDescription = document.getElementById("itemDescription").value;
        
//         if (itemName && itemPrice && itemCategory && itemDescription) {
//             menuItems.push({ name: itemName, price: parseFloat(itemPrice), category: itemCategory, description:itemDescription});
//             displayMenuItems();
//             addForm.reset();
//         }
//     });

//     // Display menu items in the list

//     function displayMenuItems() {
//         menuItemsList.innerHTML = "";
//         menuItems.forEach((item, index) => {
//             const li = document.createElement("li");
//             li.innerHTML = `${item.name} - $${item.price.toFixed(2)} - ${item.category}, ${item.description} 
//             <button onclick="editItem(${index})">Edit</button>
//             <button onclick="deleteItem(${index})">Delete</button>`;
//             menuItemsList.appendChild(li);
//         });
//     }

//     // Edit item in the menu
//     window.editItem = function (index) {
//         const item = menuItems[index];
//         currentEditIndex = index;
//         editItemName.value = item.name;
//         editItemPrice.value = item.price;
//         editModal.style.display = "flex";
//     };

    
//     // $('.saveChangesBtn').on('click', function(){$('#menuItems').fadeIn('slow');});
//     // Save changes after editing
//     saveChangesBtn.addEventListener("click", function () {   
//         const updatedName = editItemName.value;
//         const updatedPrice = parseFloat(editItemPrice.value);

//         if (updatedName && updatedPrice && currentEditIndex >= 0) {
//             menuItems[currentEditIndex] = { name: updatedName, price: updatedPrice };
//             displayMenuItems();
//             closeModal();
//         }
//     });

//     // Close the edit modal
//     closeModalBtn.addEventListener("click", closeModal);

//     function closeModal() {
//         editModal.style.display = "none";
//         currentEditIndex = -1;
//     }

//     // Delete item from the menu
//     window.deleteItem = function (index) {
//         if (confirm("Are you sure you want to delete this item?")) {
//             menuItems.splice(index, 1);
//             displayMenuItems();
//         }
//     };
// });
