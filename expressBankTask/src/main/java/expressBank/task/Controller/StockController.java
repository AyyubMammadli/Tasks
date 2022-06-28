package expressBank.task.Controller;


import expressBank.task.Service.StockService;
import expressBank.task.modul.StockWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;

@Controller
@RequestMapping("/stock")
public class StockController {


    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping(path = "/find/{tricker}")
    public StockWrapper findStock(@PathVariable("tricker") String tricker) {
        return stockService.findStock(tricker);
    }

    @PostMapping("")
    public BigDecimal findPrice(StockWrapper stock) throws IOException {

        return stockService.findPrice(stock);
    }
}


