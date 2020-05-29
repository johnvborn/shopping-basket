$(document).ready(function () {

    $("#cb").click( function (event){
        $("#create-basket-form").show();
        $("#create-item-form").hide();
        $("#delete-item-form").hide();
        $("#search-basket-item-form").hide();
        $("#total-basket-item-form").hide();
    });

    $("#ci").click( function (event){
            $("#create-basket-form").hide();
            $("#create-item-form").show();
            $("#delete-item-form").hide();
            $("#search-basket-item-form").hide();
            $("#total-basket-item-form").hide();
     });

     $("#li").click( function (event){
             $("#create-basket-form").hide();
             $("#create-item-form").hide();
             $("#delete-item-form").hide();
             $("#search-basket-item-form").show();
             $("#total-basket-item-form").hide();
    });

     $("#di").click( function (event){
             $("#create-basket-form").hide();
             $("#create-item-form").hide();
             $("#delete-item-form").show();
             $("#search-basket-item-form").hide();
             $("#total-basket-item-form").hide();
    });

     $("#tb").click( function (event){
             $("#create-basket-form").hide();
             $("#create-item-form").hide();
             $("#delete-item-form").hide();
             $("#search-basket-item-form").hide();
             $("#total-basket-item-form").show();
    });

    $("#create-basket-form").submit(function (event) {
            event.preventDefault();
            createBasket();

    });

    $("#create-item-form").submit(function (event) {
                event.preventDefault();
                createItem();

    });

    $("#delete-item-form").submit(function (event) {
                    event.preventDefault();
                    deleteItem();
    });

    $("#search-basket-item-form").submit(function (event) {
                    event.preventDefault();
                    basketItems();
    });

    $("#total-basket-item-form").submit(function (event) {
                    event.preventDefault();
                    totalBasketItems();
    });

});

function createBasket() {
    var basket = {};
    basket["basketName"] = $("#basketName").val();

    $("#btn-create-basket").prop("disabled", true);


    $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/basket",
            data: JSON.stringify(basket),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-create-basket").prop("disabled", false);

            },
            error: function (e) {

                var json = "<pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-create-basket").prop("disabled", false);

            }
        });

}

function createItem() {
    var basket = {};
    basket["basketId"] = $("#basketId").val();
    basket["itemName"] = $("#itemName").val();
    basket["itemAmount"] = $("#itemAmount").val();

    $("#btn-create-item").prop("disabled", true);


    $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/basket/item",
            data: JSON.stringify(basket),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-create-item").prop("disabled", false);

            },
            error: function (e) {

                var json = "<pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-create-item").prop("disabled", false);

            }
        });

}

function deleteItem() {
    var basket = {};
    basket["id"] = $("#itemId").val();
    console.log($("#itemId").val())
    $("#btn-delete-item").prop("disabled", true);


    $.ajax({
            type: "DELETE",
            url: "/api/basket/item/" +  $("#itemId").val(),
            cache: false,
            timeout: 600000,
            success: function () {

                var json = "<h4>Ajax Response</h4><pre>"
                    + " Deleted. " + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ");
                $("#btn-delete-item").prop("disabled", false);

            },
            error: function (e) {

                var json = "<pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-delete-item").prop("disabled", false);

            }
        });

}

function basketItems() {
    var basket = {};
    basket["id"] = $("#searchBasketId").val();

    $("#btn-search-items").prop("disabled", true);


    $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/api/basket/items/" +  $("#searchBasketId").val(),
            data: basket,
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-search-items").prop("disabled", false);

            },
            error: function (e) {

                var json = "<pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-search-items").prop("disabled", false);

            }
        });

}


function totalBasketItems() {
    var basket = {};
    basket["id"] = $("#totalBasketId").val();

    $("#btn-total-items").prop("disabled", true);


    $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/api/basket/total/" +  $("#totalBasketId").val(),
            data: basket,
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-total-items").prop("disabled", false);

            },
            error: function (e) {

                var json = "<pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-total-items").prop("disabled", false);

            }
        });

}