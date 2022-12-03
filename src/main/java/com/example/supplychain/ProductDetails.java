package com.example.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {

    public TableView<Product>  productTable; // = new TableView<>();

    public Pane getAllProducts(){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        final ObservableList<Product> data = FXCollections.observableArrayList();
        data.add(new Product(1,"OnePlus Nord CE Lite 5G",18999.00));
        data.add(new Product(2,"Samsung Glaxy A23",19999.00));
        data.add(new Product(3,"Samsung Glaxy F13 5G",12499.00));

        ObservableList<Product>  items =Product.getAllProduct();

        productTable = new TableView<>();
        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.width,SupplyChain.height);

        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width,SupplyChain.height);
        tablePane.getChildren().add(productTable);

        return tablePane;
    }


    public  Pane getProductByName(String searchName){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product>  items =Product.getProductsByName(searchName);

        productTable = new TableView<>();
        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.width,SupplyChain.height);

        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width,SupplyChain.height);
        tablePane.getChildren().add(productTable);

        return tablePane;
    }

    public Product getSelectedProduct(){
        if(productTable==null){
            System.out.println("Table object not found");
            return null;
        }
        if(productTable.getSelectionModel().getSelectedIndex() != 1){
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId() + " " +
                    selectedProduct.getName() + " " +
                    selectedProduct.getPrice() + " "
                    );
            return selectedProduct;
        }
        else{
            System.out.println("nothing selected");
            return null;
        }
    }

}
