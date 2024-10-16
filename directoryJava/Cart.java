import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductCartApp extends Application {

    // Product class to store product details
    class Product {
        String pName;
        int pPrice;
        int count;

        public Product(String pName, int pPrice) {
            this.pName = pName;
            this.pPrice = pPrice;
            this.count = 0;
        }
    }

    Product[] products = {
            new Product("product1", 100),
            new Product("product2", 200),
            new Product("product3", 300)
    };

    VBox container1 = new VBox(10);
    VBox container2 = new VBox(10);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Product Cart");

        container1.setPadding(new Insets(10));
        container2.setPadding(new Insets(10));

        // Display products
        for (int i = 0; i < products.length; i++) {
            HBox productDisplay = new HBox(10);
            Product product = products[i];

            Label nameLabel = new Label(product.pName);
            Label priceLabel = new Label("Price: " + product.pPrice);
            Label countLabel = new Label(String.valueOf(product.count));

            Button minusButton = new Button("-");
            Button plusButton = new Button("+");

            int index = i;
            minusButton.setOnAction(e -> {
                if (products[index].count > 0) {
                    products[index].count--;
                    countLabel.setText(String.valueOf(products[index].count));
                    updateCartDetails();
                }
            });

            plusButton.setOnAction(e -> {
                products[index].count++;
                countLabel.setText(String.valueOf(products[index].count));
                updateCartDetails();
            });

            productDisplay.getChildren().addAll(nameLabel, priceLabel, minusButton, countLabel, plusButton);
            container1.getChildren().add(productDisplay);
        }

        VBox mainContainer = new VBox(20);
        mainContainer.getChildren().addAll(container1, container2);

        Scene scene = new Scene(mainContainer, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Update cart details method
    public void updateCartDetails() {
        container2.getChildren().clear();

        int totalItems = 0;
        int totalPrice = 0;

        for (Product product : products) {
            if (product.count > 0) {
                totalItems += product.count;
                totalPrice += product.count * product.pPrice;

                Label productDetail = new Label(
                        product.pName + ": " + product.count + " x " + product.pPrice + " = " + (product.count * product.pPrice)
                );
                container2.getChildren().add(productDetail);
            }
        }

        Label totalDisplay = new Label("Total Items: " + totalItems + "\nTotal Price: " + totalPrice);
        container2.getChildren().add(totalDisplay);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
