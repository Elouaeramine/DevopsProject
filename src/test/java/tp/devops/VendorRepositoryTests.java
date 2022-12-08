package tp.devops;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import tp.devops.entities.Vendor;

import tp.devops.repositories.VendorRepository;

import java.util.List;



@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendorRepositoryTests {

    @Autowired
    private VendorRepository vendorRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveTest(){
        Vendor vendor = new Vendor();
        vendorRepository.save(vendor);
        Assertions.assertThat(vendor.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getVendorTest(){

        Vendor vendor = vendorRepository.findById(1L).get();

        Assertions.assertThat(vendor.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfVendorsTest(){

        List<Vendor> vendors = vendorRepository.findAll();

        Assertions.assertThat(vendors.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateVendorTest(){

        Vendor vendor = vendorRepository.findById(1L).get();

        vendor.setName("Amine");

        Vendor vendorUpdated =  vendorRepository.save(vendor);

        Assertions.assertThat(vendorUpdated.getName()).isEqualTo("Amine");

    }
}
