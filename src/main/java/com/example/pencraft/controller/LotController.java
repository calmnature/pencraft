package com.example.pencraft.controller;

import com.example.pencraft.constant.SessionConst;
import com.example.pencraft.domain.Employees;
import com.example.pencraft.domain.Lot;
import com.example.pencraft.form.EmployeesForm;
import com.example.pencraft.form.LotForm;
import com.example.pencraft.form.PassFailProductForm;
import com.example.pencraft.service.LotService;
import com.example.pencraft.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LotController {


    private final LotService lotService;
    private final ProductService productService;

    @GetMapping("/lots")
    public String showLots(Model model, @RequestParam(name = "pageNo",defaultValue = "1")int pageNo, @RequestParam(name = "pageSize",defaultValue = "10")int pageSize, HttpSession session) {

        int realPage = pageNo - 1;
        Page<Lot> page = lotService.findAllPage(realPage, pageSize);
        List<LotForm> lots = lotService.entityToDto(page.getContent());
        Employees loginEmployees = (Employees) session.getAttribute(SessionConst.LOGIN_MEMBER);


        model.addAttribute("client", loginEmployees);
        model.addAttribute("lots", lots);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());

        return "lot/show_lot";
    }


    @GetMapping("/lots/{id}/detail")
    public String detailLots(HttpServletRequest request, Model model, @PathVariable(value = "id") String id, HttpSession session) {
        Lot byLotId = lotService.findByLotId(Long.parseLong(id));
        LotForm lotForm = LotForm.toDto(byLotId);
        log.info("아이디 받아온 거 : {}", id);

        Employees loginEmployees = (Employees) session.getAttribute(SessionConst.LOGIN_MEMBER);
        EmployeesForm dto = EmployeesForm.toDto(loginEmployees);

        PassFailProductForm productHasErrors = productService.findProductHasErrors(Long.parseLong(id));


        model.addAttribute("client", dto);
        model.addAttribute("lot", lotForm);
        model.addAttribute("errorCount", productHasErrors);
        return "lot/show_lot_detail";
    }


    @PostMapping("/lots/{id}/detail")
    public String detailSave(LotForm lotForm, @PathVariable(value = "id") String id) {
        Lot entity = Lot.toEntity(lotForm);
        Lot byLotId = lotService.findByLotId(Long.parseLong(id));

        byLotId.setManager_id(entity.getManager_id());
        byLotId.setDetail(entity.getDetail());

        lotService.save(byLotId);
        return "redirect:/lots";
    }
}
