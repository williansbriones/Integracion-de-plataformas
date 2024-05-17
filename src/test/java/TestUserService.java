import com.duocuc.motopapis.dto.UserDto;
import com.duocuc.motopapis.entity.UserEntity;
import com.duocuc.motopapis.entity.UserTypeEntity;
import com.duocuc.motopapis.repository.UserRepository;
import com.duocuc.motopapis.repository.UserTypeRepository;
import com.duocuc.motopapis.service.UserServiceImpl;
import com.duocuc.motopapis.service.iface.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TestUserService {

  @Mock private UserRepository userRepository;

  @Mock private UserTypeRepository userTypeRepository;

  private UserService userService;

  @BeforeEach
  public void setUp() {
    userService = new UserServiceImpl(userRepository, userTypeRepository);
  }

  @Test
  public void testCreateUser() {

    UserDto userExpected =
        UserDto.builder()
            .id(1L)
            .name("Walter")
            .lastName("White")
            .email("Wal.whithe@gmail.com")
            .password("12345678")
            .idUserType(1L)
            .invoices(null)
            .build();
    UserDto userTest =
        UserDto.builder()
            .name("Walter")
            .lastName("White")
            .email("Wal.whithe@gmail.com")
            .password("12345678")
            .build();
    Mockito.when(userTypeRepository.findById(1L))
        .thenReturn(
            java.util.Optional.of(
                UserTypeEntity.builder().id(1L).name("Admin").description("Admin").build()));
    Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(UserEntity.builder().id(1L).build());
    UserDto result = userService.createUser(userTest);
    System.out.println(result.id());
    Assertions.assertNull(result.id());
  }
}
