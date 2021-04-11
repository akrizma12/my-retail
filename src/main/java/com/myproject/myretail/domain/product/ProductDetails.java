package com.myproject.myretail.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDetails {
    private Product product;

    @Setter
    @Getter
    public static class Product {
        private Item item;

        @JsonProperty("available_to_promise_network")
        private AvailableToPromiseNetwork availableToPromiseNetwork;

        @Getter
        @Setter
        public static class Item {
            @JsonProperty("product_description")
            private ProductDescription productDescription;

            @Getter
            @Setter
            public static class ProductDescription {
                private String title;
            }
        }

        @Getter
        @Setter
        public static class AvailableToPromiseNetwork {
            @JsonProperty("product_id")
            private String productId;
        }
    }


}
