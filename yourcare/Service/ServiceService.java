package com.example.yourcare.Service;

import com.example.yourcare.Model.*;
import com.example.yourcare.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {
    private final ServicesRepository servicesRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final StackRepository stackRepository;
    private final AdminRepository adminRepository;
    public List<Services>getAllService(){
        return servicesRepository.findAll();
    }
    public String addService(Services services){
        User user=userRepository.findUserByIdUser(services.getIdUser());
        Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(services.getIdHospital());
        if(user==null){
            return "The user is Not Exist";
        }
        if(hospital==null){
            return "stack Not Exist";
        }
        servicesRepository.save(services);
        return "true";
    }
    public boolean updateService(Integer id,Services services){
        Services services1=servicesRepository.findServicesByIdServices(id);
        if(services1==null){
            return false;
        }
        services1.setServiceType(services.getServiceType());
        services1.setAuxiliaryTools(services.getAuxiliaryTools());
        services1.setIdUser(services.getIdUser());
        services1.setIdHospital(services.getIdHospital());
        servicesRepository.save(services1);
        return true;
    }
    public boolean deleteService(Integer id){
        Services services=servicesRepository.findServicesByIdServices(id);
        if(services==null){
            return false;
        }
        servicesRepository.delete(services);
        return true;
    }
    //6:If the user has special needs, a chair with special needs is chosen or alerted to modify its choice
    public String OrderChair(Services services){
        User user=userRepository.findUserByIdUser(services.getIdUser());
        if(user==null){
            return "User Not Found";
        }
        if(user.getStatus().equals("Spical needs")&&services.getAuxiliaryTools().equals("Special Needs Chair")&&user.getAge()<60){
            servicesRepository.save(services);
            return "The chair has been successfully ordered.";
        }
        return "Valid: This chair is for people with special needs.";
    }
    //7 :If the user is old, a chair is chosen for the elderly or he is alerted to modify his choice
    public String OrderChairElderly(Services services){
        User user=userRepository.findUserByIdUser(services.getIdUser());
        if(user==null){
            return "User Not Found";
        }
        if(user.getStatus().equals("Elderly")&&services.getAuxiliaryTools().equals("Special chair for the elderly")&&user.getAge()>=60){
            servicesRepository.save(services);
            return "The chair has been successfully ordered.";
        }
        return "You should choose a chair for Elderly ";
    }
    //8:If the user asked for the type of service auxiliary tools and did not specify the type of tool alert him
public String selectService(Services services){
        User user=userRepository.findUserByIdUser(services.getIdUser());
        if(user==null){
            return "User Not Found";
        }
        if(services.getServiceType().equals("auxiliaryTools")&&services.getAuxiliaryTools().equals("null")){
            return "Please select the appropriate service";
        }
        servicesRepository.save(services);
        return "Success";
}
//9:Nurse Request
public String RequestNurse(Services services) {
    User user = userRepository.findUserByIdUser(services.getIdUser());
    Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(services.getIdHospital());
    Stack stack= stackRepository.findServiceStoreById(services.getIdHospital());
    if (user == null) {
        return "User Not Found";
    }
    if(hospital==null){
        return "hospital Not Exist";
    }if(stack==null){
        return "stack Not Exist";
    }
    if (services.getServiceType().equals("Nurse")&&stack.getNumberNurse()>=services.getNumberNurse()) {
      stack.setNumberNurse(stack.getNumberNurse()-services.getNumberNurse());
      servicesRepository.save(services);
      stackRepository.save(stack);
        return "The nurse has been successfully requested.";
    }
    return "The number of nurses you requested is not available.";
}
//10:Ambulance Request

public String RequsetAmbulans(Services services){
  User user=userRepository.findUserByIdUser(services.getIdUser());
    Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(services.getIdHospital());
    Stack stack= stackRepository.findServiceStoreById(services.getIdHospital());
  if(user==null){
      return "User Not Found";
  }
    if(hospital==null){
        return "hospital null";
    }
  if(stack.getNumberAmbulance()>=1&&services.getServiceType().equals("ambulance")){
      stack.setNumberAmbulance(stack.getNumberAmbulance()-1);
      servicesRepository.save(services);
      return "The Ambulance has been successfully requested.";
  }
  return "There are no ambulances currently.";
}
//11:In case the patient is old and he asks for a nurse and an ambulance and does not ask for help tools, he will be given a chair for the elderly as a gift
    public String giftChair(Services services){
        User user=userRepository.findUserByIdUser(services.getIdUser());
        if(user==null){
            return "User Not Found";
        }
        if(user.getAge()>=60&&user.getStatus().equals("Spical needs")&&services.getAuxiliaryTools().equals("null")){
            services.setAuxiliaryTools("Special chair for the elderly");
            servicesRepository.save(services);
            return "A chair was sent as a gift.";
        }
        servicesRepository.save(services);
        return "The service has been requested successfully.";
    }
//12: Return the number of ambulance who were requested by the user in the hospital
    public String ReturnAmb(Integer ServiceId){
        Services services=servicesRepository.findServicesByIdUser(ServiceId);
        User user=userRepository.findUserByIdUser(services.getIdUser());
        Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(services.getIdHospital());
        Stack stack= stackRepository.findServiceStoreById(services.getIdHospital());
        Admin admin=adminRepository.findAllById(hospital.getIdAdmin());
        if(user==null){
            return "User Not Found";
        }
        if(services==null){
            return "Service Not Found";
        }
        if(hospital==null){
            return "hospital Not Found";
        }if(admin!=null){
        if(user.getIdUser().equals(services.getIdUser())) {
            if (hospital.getIdHospital().equals(services.getIdHospital())) {
                if (services.getServiceType().equalsIgnoreCase("ambulance")) {
                    stack.setNumberAmbulance(stack.getNumberAmbulance() + 1);
                    servicesRepository.save(services);
                    stackRepository.save(stack);
                    return "Success";
                }
            }
            }
        }
        return "null";
    }

    //13 :Return the number of nurses who were requested by the user in the hospital
    public String ReturnNurse(Integer ServiceId){
       Services services=servicesRepository.findServicesByIdUser(ServiceId);
       User user=userRepository.findUserByIdUser(services.getIdUser());
        Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(services.getIdHospital());
        Stack stack= stackRepository.findServiceStoreById(services.getIdHospital());
        Admin admin=adminRepository.findAllById(hospital.getIdAdmin());
       if(user==null){
           return "User Not Found";
       }
       if(services==null){
           return "Service Not Found";
       }
       if(hospital==null){
           return "hospital Not Found";
       }if(admin!=null){
       if(user.getIdUser().equals(services.getIdUser())) {
           if (hospital.getIdHospital().equals(services.getIdHospital())) {
               if (services.getServiceType().equalsIgnoreCase("Nurse")) {
                   stack.setNumberNurse(stack.getNumberNurse() + services.getNumberNurse());
                   servicesRepository.save(services);
                   stackRepository.save(stack);
                   return "Success";
               }
           }
       }}
        return "null";
    }
//14 :He takes the type of tool and extracts all the users who requested it
public List<Services> getServiceBy(String tool){
List<Services> services=servicesRepository.findServicesByAuxiliaryTools(tool);
        if(services==null){
            return null;
        }for(int i=0;i<services.size();i++) {
        if (services.get(i).getAuxiliaryTools().equals(tool)){
            return services;
        }
    }
        return null;
}

    }