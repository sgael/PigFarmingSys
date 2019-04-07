package service.trial.RestWebService.Service;

import java.util.List;

import service.trial.RestWebService.Domain.Pigsty;

public interface PigstyService {
	
 Pigsty savepig(Pigsty pigsty);
 List<Pigsty> listall();
 Pigsty findOne(Long id);
 Pigsty findByOwner(String owner);
 void delete(Pigsty pigsty);

}
