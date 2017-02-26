package by.kanarski.booking.services.impl;

import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.entities.detail.Detail;
import by.kanarski.booking.services.interfaces.IFacilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class FacilityService extends ExtendedBaseService<Detail, FacilityDto> implements IFacilityService {



}
