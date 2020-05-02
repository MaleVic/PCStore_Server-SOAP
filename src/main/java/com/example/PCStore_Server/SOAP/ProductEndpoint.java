package com.example.PCStore_Server.SOAP;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ProductRepository ProductRepository;

    @Autowired
    public ProductEndpoint(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(ProductRepository.findProduct(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getALLProductsRequest")
    @ResponsePayload
    public GetALLProductsResponse getALLProducts(@RequestPayload GetALLProductsRequest request){
        GetALLProductsResponse response = new GetALLProductsResponse();

        for (Product product: ProductRepository.getAllProducts()
        ) {
            response.getAllProducts().add(product);
        }



        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
    @ResponsePayload
    public DeleteProductResponse deleteProduct(@RequestPayload DeleteProductRequest request) {
        DeleteProductResponse response = new DeleteProductResponse();

        String responseMessage =ProductRepository.deleteProduct(request.getIdProduct());
        response.setResponseMessage(responseMessage);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
    @ResponsePayload
    public AddProductResponse addProduct(@RequestPayload AddProductRequest request){
        AddProductResponse response = new AddProductResponse();

        ProductRepository.addProduct(request.getIdProduct(), request.getName(),request.getPrice());
        response.setIdProduct(request.getIdProduct());
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editProductRequest")
    @ResponsePayload
    public EditProductResponse editProduct(@RequestPayload EditProductRequest request){
        EditProductResponse response = new EditProductResponse();

        ProductRepository.editProduct(request.getOldName(), request.getNewName(),request.getNewPrice());
        response.setName(request.getNewName());
        response.setPrice(request.getNewPrice());

        return response;
    }
}
