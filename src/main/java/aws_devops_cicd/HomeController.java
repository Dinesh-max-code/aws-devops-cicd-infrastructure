package com.example.aws_devops_cicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

```
@GetMapping("/")
public String home() {
    return "AWS DevOps CI/CD Pipeline Working Successfully!😎🔥";
}
```

}
