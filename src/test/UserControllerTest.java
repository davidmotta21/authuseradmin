package test;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User("John", "Doe");
        userRepository.save(user);
        assertNotNull(user.getId());
    }
}