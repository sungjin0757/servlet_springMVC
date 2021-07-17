package study.servlettospringmvc.mvc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class MyModel {

    private final String viewPath;
    private Map<String,Object> model=new HashMap<>();

}
