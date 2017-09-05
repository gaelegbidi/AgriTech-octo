package ma.octo.agritech.services;

import ma.octo.agritech.domains.Compaign;
import ma.octo.agritech.repositories.CompaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompaignService {
    @Autowired
    private CompaignRepository compaignRepository;

    public List<Compaign> getAll() {
        List<Compaign> compaigns = new ArrayList<>();
        this.compaignRepository.findAll().forEach(compaigns::add);
        return compaigns;
    }

    public Compaign getByRef(String compaignRef) {
        return this.compaignRepository.findOneByRef(compaignRef);
    }

    public Compaign save(Compaign compaign) {
        this.compaignRepository.save(compaign);
        return compaign;
    }
}
