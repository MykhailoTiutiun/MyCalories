package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.models.Details;
import com.mykhailotiutiun.mycalories.persistence.models.User;

public interface DetailsService {
    Details getDetailsById(Long id);

    void save(User user);

    void setDetailsParams(Long id, Integer age, Integer height, Integer weight, Integer fat);

    void delete(Long id);
}
