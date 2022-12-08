package tp.devops;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import tp.devops.entities.Bill;
import tp.devops.repositories.BillRepository;

import java.util.List;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BillsRepositoryTests {

    @Autowired
    private BillRepository billRepository;

    // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveTest(){
        Bill bill = new Bill();
        billRepository.save(bill);
        Assertions.assertThat(bill.getBill_id()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getProductTest(){

        Bill product = billRepository.findById(1L).get();

        Assertions.assertThat(product.getBill_id()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfBillTest(){

        List<Bill> products = billRepository.findAll();

        Assertions.assertThat(products.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateBillTest(){

        Bill bill = billRepository.findById(1L).get();

        bill.setTax(1L);

        Bill billUpdated =  billRepository.save(bill);

        Assertions.assertThat(billUpdated.getTax()).isEqualTo(1L);

    }
}
