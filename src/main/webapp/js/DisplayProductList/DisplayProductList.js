function fetchProductList(category) {
    $.ajax({
        method: 'GET',
        url: 'productList',
        data: { category: category },
        dataType: 'json',
        success: function(response) {
            // Update the DOM with the retrieved productList
            $('#productList').empty();
            $.each(response, function(index, product) {
                var productName = product.name;
                var productDescription = product.description;
                var productId = product.id;

                // You can further process and display the product information as needed
                var productElement = '<div class="product">' +
                    '<h2 class="product-name">' + productName + '</h2>' +
                    '<p class="product-description">' + productDescription + '</p>' +
                    '</div>';
                $('#productList').append(productElement);
            });
        }
    });
}


$(document).ready(function() {
    $(".category").click(function(){
        var category = $(this).val();
        fetchProductList(category);
    });
});