package pl.jazapp.app.webapp.departments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jazapp.app.repositories.DepartmentRepository;
import pl.jazapp.app.services.departments.AddDepartmentService;
import pl.jazapp.app.services.departments.DepartmentGetAllService;
import pl.jazapp.app.services.departments.EditDepartmentService;
import pl.jazapp.app.services.login.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestScoped
@Named
public class DepartmentController {

    @Inject
    DepartmentRepository departmentRepository;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    AddDepartmentService addDepartmentService;

    @Inject
    EditDepartmentService editDepartmentService;

    @Inject
    DepartmentGetAllService departmentGetAllService;

    public List<DepartmentEntity> getDepartmentList() {
        return departmentGetAllService.getAll();
    }

    public String edit(DepartmentRequest departmentRequest) {

        Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Optional<DepartmentEntity> checkingDepartment = departmentRepository.findByName(departmentRequest.getName());

        if(checkingDepartment.isPresent() && param.containsKey("departmentId")){
            if (editDepartmentService.editDepartment(Long.parseLong(param.get("departmentId")), departmentRequest.getName())){
                return "/departments/list.xhtml";
            }
        }

        var added = addDepartmentService.createDepartment(departmentRequest.getName());

        if(added) {
            return "/departments/list.xhtml";
        }
        else {
            return "/departments/edit.xhtml";
        }
    }
}
