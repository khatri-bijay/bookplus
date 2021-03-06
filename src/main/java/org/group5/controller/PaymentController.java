package org.group5.controller;


import org.group5.model.*;
import org.group5.model.enums.PaymentType;
import org.group5.service.AccountService;
import org.group5.service.CustomerService;
import org.group5.service.OrderService;
import org.group5.service.ProductCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;

import java.util.List;

/**
 * Created by Neha on 7/11/2016.
 */
@Controller
public class PaymentController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductCopyService cartService;
    private static String PATH = "/payment";


    public static Date addDays(Date d, int days)
    {
        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
        return d;
    }

    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.GET)
    public String retrievePaymentDetails(ModelMap modelMap, @PathVariable("paymentId") long paymentId) {
        // Account account = accountService.getAccountByPaymentId(paymentId);
     /*   Order order=account.getOrderByOrderId(orderId);
        modelMap.put("order",order);*/
       /* PaymentType type=account.getPaymentInfo.PaymentType;

        if(type.equals(PaymentType.PAYPAL)){
            return  PATH +"paypal";
        }*/
        return "/paypal";

       /* if(type.equals(PaymentType.CREDITCARD)){

        }
        if(type.equals(PaymentType.DEBITCARD)){

        }
        if(type.equals(PaymentType.BANKACCOUNT)){

        }
        */
    }

    @RequestMapping(value = "/payment/success", method = RequestMethod.GET)
    public String success(ModelMap modelMap, HttpSession session, HttpServletRequest request) {

        List<ShoppingCartController.Item> itemList = (List<ShoppingCartController.Item>) session.getAttribute("cart");
        Order order = new Order();
        Date now = new Date();
        DateFormat currentDate = DateFormat.getDateInstance();
        Date deliveryDate = addDays(now, 3);


        Account account =new Account();// accountService.findById((long)customerService.findById((long)session.getAttribute("userId")).getAccount().getId());
        Address address = new Address();
       address.setCity("city");
        address.setZip("15220");
        account.setShippingAddress(account.getShippingAddress());
        accountService.add(account);
        for (ShoppingCartController.Item item : itemList) {
            OrderLine orderLine = new OrderLine();

            orderLine.setQuantity(item.getQuantity());
            orderLine.setProduct(item.getProductCopy(item.getId()).getProduct());
            orderLine.setDeliveryDate(deliveryDate);
            order.addOrderLineItem(orderLine);

            //cartService.delete(item.getProductCopy(item.getId()).getId());

        }
        order.setOrderDate(new Date());
        //  order.setOrderDate(new Date());
        order.setAccount(account);

        orderService.add(order);

        session.setAttribute("order", order);
        //System.out.println(ps.getPaypal(request).getNum_cart_items());
        return  "/success";
    }
/*
    /*@RequestMapping(value="/paypal/{paypalId}", method= RequestMethod.POST)
    public String paymentSuccess(Transaction transaction, BindingResult result, RedirectAttributes redirect){
        String view = "redirect:" + "/paypal/success";

        if(result.hasErrors()){
            redirect.addFlashAttribute("message","Please correct the following errors.");
            view ="/paypal/success";
        }

        else {
            transactionService.add(transaction);
            redirect.addFlashAttribute("message", "Book sucessfully added");
        }

        return view;
    }*//*
*/


}

