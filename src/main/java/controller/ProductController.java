package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ProductServiceImp;

import java.util.List;

@Controller
public class ProductController {

    private ProductServiceImp productServiceImp = new ProductServiceImp();

    @RequestMapping("/")
    public String loadIndex(Model model) {
        List productList = productServiceImp.findAll();
        model.addAttribute("products", productList);
        return "/index";
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @RequestMapping(value = "/product/save", method = RequestMethod.POST)
    public String save(Product product, RedirectAttributes redirectAttributes) {
        boolean flag;
        String link;
        product.setId((int) (Math.random() * 1000));
        flag = productServiceImp.save(product);
        if (flag) {
            redirectAttributes.addFlashAttribute("success", "Save product successfully!");
            link = "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("fail", "Save product fail!");
            link = "/product/save";
        }
        return link;
    }

    @RequestMapping(value = "/product/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productServiceImp.findById(id));
        return "/edit";
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String update(Product product, RedirectAttributes redirectAttributes) {
        productServiceImp.update(product.getId(), product);
        redirectAttributes.addFlashAttribute("success", "Modified product successfully!");
        return "redirect:/";
    }

    @RequestMapping(value = "/product/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", productServiceImp.findById(id));
        return "/delete";
    }

    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    public String delete(Product product, RedirectAttributes redirectAttributes) {
        productServiceImp.remove(product.getId());
        redirectAttributes.addFlashAttribute("success", "Removed product successfully!");
        return "redirect:/";
    }

    @RequestMapping(value = "/product/{id}/view", method = RequestMethod.GET)
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("product", productServiceImp.findById(id));
        return "/view";
    }
}
