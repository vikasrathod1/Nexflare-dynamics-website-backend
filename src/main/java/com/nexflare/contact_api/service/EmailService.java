//package com.nexflare.contact_api.service;
//
//import com.nexflare.contact_api.dto.ContactRequest;
//
//public interface EmailService {
//
//    void sendEmail(ContactRequest request);
//
//}

package com.nexflare.contact_api.service;

import com.nexflare.contact_api.dto.ContactRequest;

public interface EmailService {

    void sendAdminEmail(ContactRequest request);

    void sendCustomerEmail(ContactRequest request);

}