package pl.jakpoliczyc.dao.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Kahoot;
import pl.jakpoliczyc.dao.repos.KahootRepository;
import pl.jakpoliczyc.dao.services.KahootService;

import java.util.List;

@Transactional
@Service
public class KahootServiceImpl implements KahootService {

    @Autowired
    private KahootRepository kahootRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Kahoot> findAll() {
        return kahootRepository.findAll();
    }

    @Override
    public void insert(List<Kahoot> kahoots) {
        kahootRepository.insert(kahoots);
    }

    @Override
    public void delete(final long id) {
        kahootRepository.delete(id);
    }
}
