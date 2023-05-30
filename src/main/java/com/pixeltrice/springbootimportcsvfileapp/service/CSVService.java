package com.pixeltrice.springbootimportcsvfileapp.service;

import com.pixeltrice.springbootimportcsvfileapp.entity.DeveloperTutorial;
import com.pixeltrice.springbootimportcsvfileapp.payload.CSVHelper;
import com.pixeltrice.springbootimportcsvfileapp.repository.DeveloperTutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    DeveloperTutorialRepository repository;

    // stores data from cvs file to mysql db
    public void save(MultipartFile file){
        try{
            List<DeveloperTutorial> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load(){
        List<DeveloperTutorial> tutorials = repository.findAll();

        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
        return in;
    }

    public List<DeveloperTutorial> getAllTutorials(){
        return repository.findAll();
    }

}
