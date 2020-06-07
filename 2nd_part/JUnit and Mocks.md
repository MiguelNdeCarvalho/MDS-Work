-------------------------------------JUNIT---------------------------------

● Conceitos
	
	● Classes de testes
	– Uma classe de testes para cada classe
	– Pelo menos um método de testes para cada método
	– Convenção : MyClass; MyClassTest
	
	● Métodos de teste
	– Métodos que implementam um caso de testes
	– Cada método de testes deve implementar o maior nº possível de casos de testes

	● Alternativamente, podem-se criar métodos de teste para cada caso
	
	● Suite de testes: Conjunto de classes de teste
	
● Estrutura

	1. Setup do teste: Inicialização de variáveis, Definição do resultado esperado
	2. Execução: método que se quer testar
	3. Assert: Comparação entre o resultado obtido e o resultado esperado
		
	- Convenções: Nomes descritivos; O que deve (ou não acontecer)


*************************************************************
@Test
public void testMultiply() {
 // MyClass is tested
 MyClass tester = new MyClass();

 // check if multiply(10,5) returns 50
 assertEquals(50, tester.multiply(10, 5),"10 x 5 must be 50");
}
************************************************************

● @BeforeAll
	
	– JUnit 4: @BeforeClass
	– Executado antes de todos os testes de uma classe de testes
	– Usado para inicializações

@BeforeAll
public void setUpBeforeClass() {
 // run once before all test cases
}


● @AfterAll

	– JUnit 4: @AfterClass
	– Executado depois de todos os testes de uma classe de testes
	– Atividades de limpeza

@AfterAll
public void tearDownAfterClass() {
// run for one time after all test cases
}

● @BeforeEach

	– JUnit 4: @Before
	– Executado antes de cada teste de uma classe de testes
	– Setup de todos os testes de uma classe de testes
	– Setup do ambiente de execução do teste
	– exemplo: leitura de dados; inicialização de classes

@BeforeEach
public void setup() {
simpleMath = new SimpleMath();
}

● @AfterEach
	
	– JUnit 4: @After
	– Executado depois de cada teste da classe de testes
	– Limpeza (teardown) de todos os testes

@AfterEach
public void tearDown() {
simpleMath = null;
}


● @Test
	
	– Usado para “marcar”/anotar um método de testes

@Test
public void testAddition() {
assertEquals(12, simpleMath.add(7, 5));
}

@Test
public void testSubtraction() {
assertEquals(9, simpleMath.subtract(12, 3));
}

@Test
public void listEquality() {
List<Integer> expected = new ArrayList<Integer>();
expected.add(5);
List<Integer> actual = new ArrayList<Integer>();
actual.add(5);
assertEquals(expected, actual);
}

● @Test(timeout = <delay>)

	– Definir o tempo máximo de execução
	– Se o tempo exceder o valor, o teste falha

@Test(timeout = 1000)
public void testInfinity(){
while (true);
}

@Test(timeout=1000)
public final void testTimeout() throws InterruptedException{
Thread.sleep(900); //sleep for 900 ms
}


● @Test(expected = <exception>.class)
	
– Teste passa se for lançada uma exceção

@Test(expected = NullPointerException.class)
public void testInitFile() throws UserCancelException(){
Files.initFile(null);
}

● @Ignore

– Usado para ignorar testes: Testes incompletos/não terminados
– Opcional: Adicionar uma string: Justificação para o ignore

@Ignore("Not Ready to Run")
@Test
public void multiplication() {
assertEquals(15, simpleMath.multiply(3, 5));
}

● Assertions (Incluir mensagem de falha é opcional)

	- assertEquals(Object expected, Object actual)
		
		*Verifica se dois objectos são iguais

		*expected.equals(actual) == true

	- assertEquals(expected[i],actual[i]) para arrays
	
		*True: Quando os arrays têm o mesmo tamanho e todos os elementos nas mesmas posições são iguais

	- assertNull(Object object)
	
		* Verifica se um objeto é null

	- assertNotNull(Object object)
	
		* Verifica se um objeto não é null

	- assertSame(Object expected, Object actual)

		* Verifica se dois objectos são o mesmo (referencia)

	- assertNotSame(Object expected, Object actual)
		
		*Verifica se dois objectos não são o mesmo (referencia)

	- assertTrue(boolean condition)
	
		* Verifica se uma condição booleana é verdade
	
	- assertFalse(boolean condition)
	
		* Verifica se uma condição booleana é falsa

	- fail()
		
		* Obriga um teste a falhar

		* Útil quando queremos forçar um teste a falhar quando perante condições proibidas
		public static void fail();


● Usar variáveis de classes nas classes de testes

	– Usar variáveis de classe
	– Como se fossem classes “normais”

public class InvoiceTest{
private static Invoice fx, fy, fz;
private static Client c1, c2, c3;
private static Product p1, p2, p3;
// maximum error when comparing real numbers
private static final double EPSILON=0.0000001;
 ...
 ...
}


● Ser exaustivo

	– Testar o máximo possível
	– exemplo: testar todos os dias do ano

@Test
public final void testValid1(){
Calendar day = Calendar.getInstance();
day.setTime(new Date());
for (int i=1; i<=366; i++){
String dayString = day.get(Calendar.YEAR)+
"/"+(day.get(Calendar.MONTH)+1)+
"/"+day.get(Calendar.DATE);
 assertTrue(MyDate.valid(dayString), “The date was correctly set");
 day.add(Calendar.DATE, 1);
 }
}

● Lidar com excepções

	– Garantir que as excepções são lançadas quando devem ser lançadas

@Test(expected = MyDate.InvalidDateException.class)
public final void testValid2()
{
// 2011 is not a leap year!
MyDate.valid("2011/02/29");
}
@Test(expected = NumberFormatException.class)
public final void testValid3()
{
// unsupported format!
MyDate.valid("2011/February/21");
}

● Documentar os asserts

	– Útil quando os testes falham

@Test
public final void testClone()
{
 Invoice clone = (Invoice) fx.clone();
 assertEquals(fx, clone, "The invoice is equal to its clone");
 assertNotSame(fx, clone, "The clone is a distinct object");
 assertArrayEquals(fx.getLines().toArray(), clone.getLines().toArray(),
"The invoice and its clone have the same lines");
}

-------------------------------------------------Mocks-------------------------------------------------------

- Frameworks de Mocking

	● Usados para criar objetos simulados
	● Para serem usados em testes unitários
	● Substituírem os objetos reais durante os testes
	
	● Quando usar:
		
	– Interação com métodos que não tenham um comportamento determinístico
	– Interação com métodos que tenham efeitos secundários que só façam sentido em
	ambientes de produção
	– Invocar operações externas
	– Forçar erros “estranhos” e difíceis de verificar


- Mockito

	● Criar objetos de Mocks
	– Anotação @Mock

	● Especificar valor de retorno
	– “when thenReturn”
	– “when thenThrow”
	– Invocações não especificadas devolvem valores nulos de acordo com o tipo
		
		● null para objetos
		● 0 para números
		● false para booleanos
		● colecções vazias para colecções
		●etc

********************************************************
import static org.mockito.Mockito.*;
public class MockitoTest {
 @Mock
 MyDatabase databaseMock;
 @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
 @Test
 public void testQuery() {
 ClassToTest t = new ClassToTest(databaseMock);
 boolean check = t.query("* from t");
 assertTrue(check);
 verify(databaseMock).query("* from t");
 }
}
********************************************************

	● Especificar comportamentos
		
		– Valores de retorno
		– Diferentes para cada método

	● Dependendo dos argumentos

		– Possível ignorar os argumentos

	● API “fluente”

		– when(...).thenReturn(…)
		– doReturn(…).when(…).methodCall

● when(...).thenReturn(...)

@Test
public void test1() {
 // create mock
 MyClass test = mock(MyClass.class);
 // define return value for method getUniqueId()
 when(test.getUniqueId()).thenReturn(43);
 // use mock in test....
 assertEquals(test.getUniqueId(), 43);
}


// demonstrates the return of multiple values
@Test
public void testMoreThanOneReturnValue() {
 Iterator<String> i= mock(Iterator.class);
 when(i.next()).thenReturn("Mockito").thenReturn("rocks");
 String result= i.next()+" "+i.next();
 //assert
 assertEquals("Mockito rocks", result);
}


// this test demonstrates how to return values based on the input
@Test
public void testReturnValueDependentOnMethodParameter() {
 Comparable<String> c= mock(Comparable.class);
 when(c.compareTo("Mockito")).thenReturn(1);
 when(c.compareTo("Eclipse")).thenReturn(2);
 //assert
 assertEquals(1, c.compareTo("Mockito"));
}


@Test
public void testReturnValueInDependentOnMethodParameter() {
 Comparable<Integer> c= mock(Comparable.class);
 when(c.compareTo(anyInt())).thenReturn(-1);
 //assert
 assertEquals(-1, c.compareTo(9));
}
// return a value based on the type of the provide parameter
@Test
public void testReturnValueInDependentOnMethodParameter2() {
 Comparable<Todo> c= mock(Comparable.class);
 when(c.compareTo(isA(Todo.class))).thenReturn(0);
 //assert
 assertEquals(0, c.compareTo(new Todo(1)));
}

● when(...).thenThrow(...)

Properties properties = mock(Properties.class);
when(properties.get("Anddroid")).thenThrow(new IllegalArgumentException(...));
Try {
 properties.get("Anddroid");
 fail("Anddroid is misspelled");
} catch (IllegalArgumentException ex) {
 // good!
}

● doReturn(…).when(…).methodCall

	– Útil quando usado juntamente com Spy
		
@Test
public void testLinkedListSpyCorrect() {
 // Lets mock a LinkedList
 List<String> list = new LinkedList<>();
 List<String> spy = spy(list);
 // You have to use doReturn() for stubbing
 doReturn("foo").when(spy).get(0);
 assertEquals("foo", spy.get(0));
}

● Verificar invocação de métodos

@Test
public void testVerify() {
 // create and configure mock
 MyClass test = Mockito.mock(MyClass.class);
 when(test.getUniqueId()).thenReturn(42);
 // call method testing on the mock with parameter 12
 test.testing(12);
 test.getUniqueId();
 test.getUniqueId();
 // now check if method testing was called with the parameter 12
 verify(test).testing(ArgumentMatchers.eq(12));
 // was the method called twice?
 verify(test, times(2)).getUniqueId();
 // other alternatives for verifiying the number of method calls for a method
 verify(test, never()).someMethod("never called");
 verify(test, atLeastOnce()).someMethod("called at least once");
 verify(test, atLeast(2)).someMethod("called at least twice");
 verify(test, times(5)).someMethod("called five times");
 verify(test, atMost(3)).someMethod("called at most 3 times");
 // This let's you check that no other methods where called on this object.
 // You call it after you have verified the expected method calls.
 verifyNoMoreInteractions(test);
}

● Testar métodos estáticos

	– Fazer mock da classe NetwordReader

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
@RunWith( PowerMockRunner.class )
@PrepareForTest( NetworkReader.class )
public class MyTest {
// Find the tests here
@Test
public void testSomething() {
 mockStatic( NetworkReader.class );
 when( NetworkReader.getLocalHostname() ).andReturn( "localhost" );
 // now test the class which uses NetworkReader
}






















