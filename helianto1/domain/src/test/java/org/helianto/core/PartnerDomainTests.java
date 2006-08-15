/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core;

import java.util.Date;
import java.util.HashSet;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.PhoneType;
import org.helianto.core.type.ShareMode;

public class PartnerDomainTests extends DomainTestSupport {
    
    public void testPartnerData() {
        PartnerData partnerData = new PartnerData();
        partnerData.setId(Long.MAX_VALUE);
        partnerData.setId(Long.MIN_VALUE);

        partnerData.setEntity(new Entity());
        
        partnerData.setAlias("TEST");
        
        partnerData.setPartners(new HashSet<Partner>());

        partnerData.setProvince(new Province());

        partnerData.setAddress1("");
        partnerData.setAddress2("");
        partnerData.setAddress3("");

        partnerData.setCityName("");

        partnerData.setPostalCode("");

        partnerData.setPostOfficeBox("");
    }

    public void testPartnerDataEquals() {
        PartnerData copy, partnerData = new PartnerData();
        partnerData.setEntity(new Entity());
        partnerData.setAlias("TEST");
        copy = (PartnerData) minimalEqualsTest(partnerData);
        
        copy.setEntity(partnerData.getEntity());
        assertFalse(partnerData.equals(copy));
        
        copy.setEntity(null);
        copy.setAlias("TEST");
        assertFalse(partnerData.equals(copy));
        
        copy.setEntity(partnerData.getEntity());
        copy.setAlias("TEST");
        assertTrue(partnerData.equals(copy));
    }

    public void testPartner() {
        Partner partner = new Partner();
        partner.setId(Long.MAX_VALUE);
        partner.setId(Long.MIN_VALUE);

        partner.setPartnerData(new PartnerData());
        
        partner.setPartnerState(ActivityState.ACTIVE.getValue());
        partner.setPartnerState(ActivityState.CANCELLED.getValue());
        partner.setPartnerState(ActivityState.INITIAL.getValue());
        partner.setPartnerState(ActivityState.SUSPENDED.getValue());
        
        partner.setPriority('A');
        
        partner.setSequence((byte) 1);
        
        partner.setProfile("");
    }

    public void testPartnerEquals() {
        Partner copy, partner = new Partner();
        partner.setPartnerData(new PartnerData());
        partner.setSequence((byte) 1);
        copy = (Partner) minimalEqualsTest(partner);
        
        copy.setPartnerData(partner.getPartnerData());
        assertFalse(partner.equals(copy));

        copy.setPartnerData(null);
        copy.setSequence(partner.getSequence());
        assertFalse(partner.equals(copy));

        copy.setPartnerData(partner.getPartnerData());
        copy.setSequence(partner.getSequence());
        assertTrue(partner.equals(copy));
    }

    public void testPartnerSubclasses() {
        new Division();
        new Customer();
        new Supplier();
        new Bank();
        new Manufacturer();
        new Agent();
    }

    public void testContact() {
        Contact contact = new Contact();
        contact.setId(Long.MAX_VALUE);
        contact.setId(Long.MIN_VALUE);

        contact.setPartnerData(new PartnerData());
        contact.setIdentity(new Identity());
        contact.setJobReference("");
        contact.setDepartment("");
        contact.setPriority(' ');
    }

    public void testContactEquals() {
        Contact copy, contact = new Contact();
        contact.setPartnerData(new PartnerData());
        contact.setIdentity(new Identity());
        copy = (Contact) minimalEqualsTest(contact);
        
        copy.setPartnerData(contact.getPartnerData());
        assertFalse(contact.equals(copy));

        copy.setPartnerData(null);
        copy.setIdentity(contact.getIdentity());
        assertFalse(contact.equals(copy));

        copy.setPartnerData(contact.getPartnerData());
        copy.setIdentity(contact.getIdentity());
        assertTrue(contact.equals(copy));
    }

    public void testPhone() {
        Phone phone = new Phone();
        phone.setId(Long.MAX_VALUE);
        phone.setId(Long.MIN_VALUE);

        phone.setContact(new Contact());
        phone.setPhoneNumber("");
        phone.setAreaCode("");
        
        phone.setPhoneType(PhoneType.BRANCH.getValue());
        phone.setPhoneType(PhoneType.FAX.getValue());
        phone.setPhoneType(PhoneType.MAIN.getValue());
        phone.setPhoneType(PhoneType.MOBILE.getValue());
        phone.setPhoneType(PhoneType.PERSONAL.getValue());
        
        phone.setComment("");
   }

    public void testPhoneEquals() {
        Phone copy, phone = new Phone();
        phone.setContact(new Contact());
        phone.setPhoneNumber("TEST");
        phone.setAreaCode("TEST");
        copy = (Phone) minimalEqualsTest(phone);
        
        copy.setContact(new Contact());
        assertFalse(phone.equals(copy));

        copy.setContact(phone.getContact());
        copy.setPhoneNumber("TEST");
        assertFalse(phone.equals(copy));

        copy.setContact(null);
        copy.setPhoneNumber("TEST");
        copy.setAreaCode("TEST");
        assertFalse(phone.equals(copy));

        copy.setContact(phone.getContact());
        copy.setPhoneNumber("");
        copy.setAreaCode("TEST");
        assertFalse(phone.equals(copy));

        copy.setContact(phone.getContact());
        copy.setPhoneNumber("TEST");
        copy.setAreaCode("");
        assertFalse(phone.equals(copy));

        copy.setContact(phone.getContact());
        copy.setPhoneNumber("TEST");
        copy.setAreaCode("TEST");
        assertTrue(phone.equals(copy));
    }

    public void testPartnerKey() {
        PartnerKey partnerKey = new PartnerKey();
        partnerKey.setId(Long.MAX_VALUE);
        partnerKey.setId(Long.MIN_VALUE);

        partnerKey.setPartner(new Partner());
        
        partnerKey.setKeyType(new KeyType());
        
        partnerKey.setKeyValue("");
    }

    public void testPartnerKeyEquals() {
        PartnerKey copy, partnerKey = new PartnerKey();
        partnerKey.setPartner(new Partner());
        partnerKey.setKeyType(new KeyType());
        copy = (PartnerKey) minimalEqualsTest(partnerKey);
        
        copy.setPartner(partnerKey.getPartner());
        assertFalse(partnerKey.equals(copy));
        
        copy.setPartner(null);
        copy.setKeyType(partnerKey.getKeyType());
        assertFalse(partnerKey.equals(copy));
        
        copy.setPartner(partnerKey.getPartner());
        copy.setKeyType(partnerKey.getKeyType());
        assertTrue(partnerKey.equals(copy));
    }

    public void testSharedEntity() {
        SharedEntity sharedEntity = new SharedEntity();
        sharedEntity.setId(Long.MAX_VALUE);
        sharedEntity.setId(Long.MIN_VALUE);

        sharedEntity.setSharedEntity(new Entity());
        
        sharedEntity.setPartnerData(new PartnerData());
        
        sharedEntity.setShareMode(ShareMode.RESTRICTED.getValue());
        sharedEntity.setShareMode(ShareMode.FULL.getValue());
        
        sharedEntity.setSharedSince(new Date());
    }

    public void testSharedEntityEquals() {
        SharedEntity copy, sharedEntity = new SharedEntity();
        sharedEntity.setSharedEntity(new Entity());
        sharedEntity.setPartnerData(new PartnerData());
        copy = (SharedEntity) minimalEqualsTest(sharedEntity);
        
        copy.setSharedEntity(sharedEntity.getSharedEntity());
        assertFalse(sharedEntity.equals(copy));
        
        copy.setSharedEntity(null);
        copy.setPartnerData(sharedEntity.getPartnerData());
        assertFalse(sharedEntity.equals(copy));
        
        copy.setSharedEntity(sharedEntity.getSharedEntity());
        copy.setPartnerData(sharedEntity.getPartnerData());
        assertTrue(sharedEntity.equals(copy));
    }

}
