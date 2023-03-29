package test;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUser(Long id) {
        List<User> users = userRepository.findAll();
        assertNotNull(users.getSize());
    }

    @Test
    public void testGetUsers() {
        User user = userRepository.findById(id).orElse(new User());
        assertNotNull(user.getId());
    }

    @Test
    public void testCreateUser(SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getDateBirth(),
                signUpRequest.getAddress(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getMobilePhone(),
                signUpRequest.getEmail(),
                signUpRequest.getActive());
        userRepository.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void testDeleteUser(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        userRepository.delete(user);
        assertNotNull(user.getId());
    }

    @Test
    public void testUpdateUser(SignupRequest signUpRequest) {
        User user;
        user = userRepository.findById(signupRequest.getId()).orElse(null);

        user.setUsername(signupRequest.getUsername());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setDateBirth(signupRequest.getDateBirth());
        user.setAddress(signupRequest.getAddress());
        user.setMobilePhone(signupRequest.getMobilePhone());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setActive(signupRequest.getActive());
        userRepository.save(user);
        assertNotNull(user.getId());
    }
}