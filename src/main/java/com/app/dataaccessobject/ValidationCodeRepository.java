package com.app.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.app.domainobject.ValidationCode;

public interface ValidationCodeRepository extends CrudRepository<ValidationCode, Long>{

	ValidationCode findByValidationCodeAndUserIdAndActive(String candidateCode, Long userId, boolean b);

}
