package service.trial.RestWebService.PigstyController;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.trial.RestWebService.Domain.Pigsty;
import service.trial.RestWebService.Service.PigstyService;

@Controller
public class PigstyController {

	@Autowired
	private PigstyService pigstyService;
	
	 
	private static final Logger logger = LoggerFactory.getLogger(PigstyController.class);

	
	@RequestMapping(value="/BranchForm",method=RequestMethod.GET)
	public String newreg(ModelMap model) {
		Pigsty pi=new Pigsty();
		model.addAttribute("pi",pi);
		return "BranchForm";
	}
	@RequestMapping(value="/BranchForm",method=RequestMethod.POST)
	public String save(@ModelAttribute("pi") @Valid Pigsty pi,BindingResult result,ModelMap model,RedirectAttributes redirectAttributes) {
		if(result.hasErrors())
		{
			logger.error("something went wrong");
			redirectAttributes.addFlashAttribute("error","something went0 wrong");
			return "/BranchForm";
			
		}
			pigstyService.savepig(pi);
			logger.info("well saved");
			redirectAttributes.addFlashAttribute("info","well done");
			return"redirect:/tables";
	}
	@RequestMapping("/tables")
	public String viewpigsty(Model model) {
		List<Pigsty> list = pigstyService.listall();
		model.addAttribute("list", list);
		return "tables";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String enable(@ModelAttribute("owner") String owner, @ModelAttribute("location") String location, @ModelAttribute("pen") String pen,
			@ModelAttribute("meters") String meters, Model model) {
		Pigsty pig = pigstyService.findByOwner(owner);
		System.out.println("ngiyi"+pig.getOwner());
		pig.setLocation(location);
//		System.out.println("locatiom"+pig.getLocation()+"==============================");
		pig.setOwner(owner);
		pig.setPen(pen);
		pig.setMeters(Double.parseDouble(meters));
		pigstyService.savepig(pig);
		model.addAttribute("pig",pig);
		return "redirect:/tables";
	}
	@RequestMapping(value = "/delete")
	public String disable(@RequestParam("id") Long id) {
		Pigsty pig = pigstyService.findOne(id);
		System.out.println("ngiyi"+pig.getOwner());
		pigstyService.delete(pig);
		return "redirect:/tables";
	}
	
}
