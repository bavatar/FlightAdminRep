package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/")
    public String flightList(Model model){
        model.addAttribute("flights", flightRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addFlight(Model model){
        model.addAttribute("flight", new Flight());
        return "flightform";
    }

    @PostMapping("/processsearch")
    public String searchResult(Model model, @RequestParam(name="SearchSelector") String optionvalue,
                                    @RequestParam(name="search") String search,
                                    @RequestParam(name="searchD") String searchD){
//        ArrayList<Flight> flightsArrayL = new ArrayList<>();
//        flightsArrayL = flightRepository.findAll();

        if (optionvalue.equals("Date")){
//            System.out.println("in searchResult");
//            System.out.println(searchD);

            Date dateIn=null;

            // convert searchD to Date then dateIn=new value
            try {
                String pattern = "yyyy-MM-dd";
                String formattedDate = searchD;
//                System.out.println(formattedDate);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                dateIn = simpleDateFormat.parse(formattedDate);
            }
            catch (java.text.ParseException e){
                e.printStackTrace();
            }

            ArrayList<Flight> flightsArrayD = flightRepository.findFlightByDate(dateIn);
            model.addAttribute("flights", flightsArrayD);
        }
        else if (optionvalue.equals("Price")){
            ArrayList<Flight> flightsArrayL = flightRepository.findFlightByPriceContaining(search);
            // Business Logic to modify flightsArrayL

            model.addAttribute("flights", flightsArrayL);
//            model.addAttribute("flights", flightRepository.findFlightByPrice(search));
        }
        else if (optionvalue.equals("ArrivalLoc")){
            model.addAttribute("flights", flightRepository.findFlightByEndAirportContainingIgnoreCase(search));
        }
        else if (optionvalue.equals("Airline")) {
            model.addAttribute("flights", flightRepository.findFlightByAirlineContainingIgnoreCase(search));
        }
        else if (optionvalue.equals("DateAndDest"))
        {
            System.out.println("Dest: " + search);
//            System.out.println("Date: " + searchD);
            Date dateIn=null;

            try {
                String pattern = "yyyy-MM-dd";
                String formattedDate = searchD;
                System.out.println("Date: " + formattedDate);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                dateIn = simpleDateFormat.parse(formattedDate);
            }
            catch (java.text.ParseException e){
                e.printStackTrace();
            }
//ArrayList<Flight> findFlightByDateAAndEndAirport(Date date, String dest);
            ArrayList<Flight> flightsArrayDate = flightRepository.findFlightByDateAndEndAirportIgnoreCase(dateIn, search);
//            ArrayList<Flight> flightsArrayDest = flightRepository.findFlightByEndAirportContainingIgnoreCase(search);
            model.addAttribute("flights", flightsArrayDate);
        }

//        flightRepository.findFlight
       return "searchlist";
    }

    @PostMapping("/processflight")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name = "date")
            String date){
        String pattern = "yyyy-MM-dd";
        System.out.println(date);
        try {
            String formattedDate = date.substring(1,date.length());
            System.out.println(formattedDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(formattedDate);
            flight.setDate(realDate);
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }

        flightRepository.save(flight);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "flightform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        flightRepository.deleteById(id);
        return "redirect:/";
    }
//
//     model.addAttribute("messages", messageRespository.findByTitle(search));
//        return "searchlist";
////                              @RequestParam(name = "endAirport"), String endAirport){)
//    public String processForm(@ModelAttribute Flight flight,
//                              @RequestParam(name = "date"), String date,
//                              @RequestParam(name = "endAirport"), String endAirport){
//        String pattern = "yyyy-MM-dd";
//        return "searchlist";
//    }
}
