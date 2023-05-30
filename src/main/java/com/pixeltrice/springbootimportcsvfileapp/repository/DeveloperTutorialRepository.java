package com.pixeltrice.springbootimportcsvfileapp.repository;

import com.pixeltrice.springbootimportcsvfileapp.entity.DeveloperTutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperTutorialRepository extends JpaRepository<DeveloperTutorial, Long> {
}
