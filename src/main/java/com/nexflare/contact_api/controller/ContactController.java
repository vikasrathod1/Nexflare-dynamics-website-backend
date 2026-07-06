////package com.nexflare.contact_api.controller;
////
////import com.nexflare.contact_api.dto.ContactRequest;
////import com.nexflare.contact_api.service.EmailService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/api/contact")
////@CrossOrigin(origins = "http://localhost:5173")
////public class ContactController {
////
////    @Autowired
////    private EmailService emailService;
////
////    @GetMapping("/test")
////    public String test() {
////        return "Contact Controller Working";
////    }
////
////    @PostMapping("/send")
////    public String send(@RequestBody ContactRequest request) {
////
////        emailService.sendEmail(request);
////
////        return "Message Received Successfully";
////    }
////}
//
//package com.nexflare.contact_api.controller;
//
//import com.nexflare.contact_api.dto.ContactRequest;
//import com.nexflare.contact_api.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/contact")
//@CrossOrigin(origins = "http://localhost:5173")
//public class ContactController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @GetMapping("/test")
//    public String test() {
//        return "Contact Controller Working";
//    }
//
//    @PostMapping("/send")
//    public ResponseEntity<Map<String, Object>> send(@RequestBody ContactRequest request) {
//
//        // Send email to Admin
//        emailService.sendAdminEmail(request);
//
//        // Send Thank You email to Customer
//        emailService.sendCustomerEmail(request);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", true);
//        response.put("message", "Thank you for contacting Nexflare Dynamics. Our team will contact you shortly.");
//
//        return ResponseEntity.ok(response);
//    }
//}


package com.nexflare.contact_api.controller;

import com.nexflare.contact_api.dto.ContactRequest;
import com.nexflare.contact_api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/test")
    public String test() {
        return "Contact Controller Working";
    }

//    @PostMapping("/send")
//    public ResponseEntity<Map<String, Object>> send(@RequestBody ContactRequest request) {
//
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//
//            emailService.sendAdminEmail(request);
//            emailService.sendCustomerEmail(request);
//
//            response.put("success", true);
//            response.put("message", "Thank you for contacting Nexflare Dynamics. Our team will contact you shortly.");
//
//            return ResponseEntity.ok(response);
//
//        } catch (Exception e) {
//
//            response.put("success", false);
//            response.put("message", "Unable to send your message. Please try again later.");
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//}

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> send(@RequestBody ContactRequest request) {

        Map<String, Object> response = new HashMap<>();

        try {

            emailService.sendAdminEmail(request);
            emailService.sendCustomerEmail(request);

            response.put("success", true);
            response.put("message", "Thank you for contacting Nexflare Dynamics. Our team will contact you shortly.");

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            // Print full exception in Render Logs
            e.printStackTrace();

            response.put("success", false);
            response.put("message", e.getMessage()); // Temporary for debugging

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}