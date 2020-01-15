package com.example;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class WebController  implements WebMvcConfigurer {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AdresseRepository adresseRepository;


    @GetMapping("/index")
    public String index()
    {
        return "index";
    }

    @ModelAttribute("contacts")
    public Iterable<Contact> getSomeList(){
        return contactRepository.findAll();
    }

    @GetMapping("/AddContact")
    public String showForm(ContactForm contactForm) {
        return "form";
    }

    @GetMapping("/ModifContact")
    public String showForm(ContactForm contactForm, @RequestParam long id, HttpServletRequest request) {



       Contact contact = contactRepository.findById(id);
       //Adresse adresse = adresseRepository.

       contactForm.setFirstName(contact.getFirstName());
       contactForm.setLastName(contact.getLastName());
       contactForm.setId(contact.getId());
       contactForm.setAddressList(new ArrayList<>());
        for (Adresse c : contact.getAdresses())
        {
            contactForm.getAddressList().add(c.getAdresse());
        }



        return "form";
    }



    @PostMapping("/ValideForm")
    public String checkPersonInfo(@Valid ContactForm contactForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        Contact c=new Contact();
        c.setFirstName(contactForm.getFirstName());
        c.setLastName(contactForm.getLastName());
        c.setId(contactForm.getId());
        c.setAdresses(new ArrayList<>());
        for (String a : request.getParameterMap().get("addresses"))
        {
            Adresse address=new Adresse(a);
            address.setAdresse(a);
            adresseRepository.save(address);


            c.getAdresses().add(address);
        }
        contactRepository.save(c);

        return "redirect:/index";
    }

    @GetMapping("/SupprimeContact")
    public String SupContact(ContactForm contactForm, @RequestParam long id) {

        Contact contact = contactRepository.findById(id);
        contactRepository.delete(contact);

        return "redirect:/index";
    }



    @GetMapping(value = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getContactById(@RequestParam() String action,@RequestParam(defaultValue = "-1", required = false) long id) {
        switch (action)
        {
            case "listContacts":
                return listContacts();
            case "getContact" :
                return contactRepository.findById(id);
            case "delContact" :
                return supContactById(id);
            default:
                return null;
        }

    }


    public ListContacts listContacts(){
        return new ListContacts(contactRepository.findAll());
    }


    public ListContacts supContactById(@PathVariable long id) {
        Contact contact = contactRepository.findById(id);
        contactRepository.delete(contact);

        return new ListContacts(contactRepository.findAll());
    }


}
