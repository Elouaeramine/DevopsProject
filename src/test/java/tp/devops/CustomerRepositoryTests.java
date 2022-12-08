package tp.devops;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import tp.devops.entities.Customer;
import tp.devops.repositories.CustomerRepository;
import java.util.List;



@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveTest(){
        Customer customer = new Customer();
        customerRepository.save(customer);
        Assertions.assertThat(customer.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getCustomerTest(){

        Customer customer = customerRepository.findById(1L).get();

        Assertions.assertThat(customer.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfCustomersTest(){

        List<Customer> customers = customerRepository.findAll();

        Assertions.assertThat(customers.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCustomerTest(){

        Customer customer = customerRepository.findById(1L).get();

        customer.setName("sarah");

        Customer customerUpdated =  customerRepository.save(customer);

        Assertions.assertThat(customerUpdated.getName()).isEqualTo("sarah");

    }
}
