# Proiect Final - Testare Automata

Proiect final pentru cursul de Testare Automata. Aplicatie de testare automata construita in Java cu Selenium WebDriver si TestNG, ce acopera atat teste UI cat si teste API, urmand pattern-ul **Page Object Model (POM)** si genereaza rapoarte de executie cu **Allure**.

## Aplicatiile testate

- **UI:** [SauceDemo](https://www.saucedemo.com/) - aplicatie demo de e-commerce
- **API:** [DemoQA](https://demoqa.com) - API-uri publice de tip BookStore si Account

## Tehnologii folosite

| Tehnologie | Versiune | Rol |
| --- | --- | --- |
| Java | 25 | Limbaj de programare |
| Maven | 3.9.12 | Build & dependency management |
| Selenium WebDriver | 4.41.0 | Automatizare browser pentru testele UI |
| TestNG | 7.12.0 | Framework de testare |
| REST Assured | 6.0.0 | Testare API |
| Allure Reports | 2.27.0 | Generare rapoarte HTML |
| AspectJ Weaver | 1.9.22.1 | Suport pentru adnotari Allure |
| Lombok | 1.18.38 | Reducere cod boilerplate |

## Structura proiectului

```
AutomationProiectFinal/
├── src/
│   └── test/
│       ├── java/
│       │   ├── pages/                  # Page Object Model
│       │   │   ├── BasePage.java       # Clasa de baza pentru toate paginile
│       │   │   ├── LoginPage.java
│       │   │   ├── InventoryPage.java
│       │   │   ├── ProductPage.java
│       │   │   └── CartPage.java
│       │   ├── tests/
│       │   │   ├── ui/                 # Teste UI (SauceDemo)
│       │   │   │   ├── LoginTest.java
│       │   │   │   ├── ProductTest.java
│       │   │   │   └── CartTest.java
│       │   │   └── api/                # Teste API (DemoQA)
│       │   │       ├── CreateAccountTest.java
│       │   │       ├── GenerateTokenTest.java
│       │   │       └── GetBooksTest.java
│       │   └── utils/
│       │       └── BaseTest.java       # Setup/teardown driver + screenshot
│       └── resources/
│           ├── testng.xml              # Configurare suite TestNG
│           ├── allure.properties       # Configurare Allure
│           ├── environment.properties  # Info mediu (apare in raport)
│           └── categories.json         # Categorii pentru failed tests
├── allure-results/                     # Rezultate brute Allure (generate)
├── allure-report/                      # Raport HTML final (generat)
├── target/                             # Fisiere build Maven
└── pom.xml
```

## Scenarii de testare

### Teste UI (SauceDemo) - 8 teste

| # | Test | Descriere |
| --- | --- | --- |
| 1 | `testLoginValid` | Verifica login reusit cu credentiale valide |
| 2 | `testLoginInvalid` | Verifica mesajul de eroare la credentiale invalide |
| 3 | `testAddProductToCart` | Adauga un produs in cos din pagina de detaliu |
| 4 | `testSortProductsAZ` | Sorteaza produsele alfabetic A-Z |
| 5 | `testLogout` | Verifica delogare prin meniul lateral |
| 6 | `testCartBadgeAfterAddProduct` | Verifica badge-ul cosului dupa adaugare produs |
| 7 | `testRemoveProductFromCart` | Elimina un produs din cos |
| 8 | `testSortProductsZA` | Sorteaza produsele alfabetic Z-A |

### Teste API (DemoQA) - 7 teste

| # | Test | Descriere |
| --- | --- | --- |
| 1 | `testCreateAccountSuccess` | Creeaza un cont nou cu date valide |
| 2 | `testCreateAccountInvalidPassword` | Verifica respingerea parolei slabe |
| 3 | `testGenerateTokenSuccess` | Genereaza token de autentificare |
| 4 | `testGenerateTokenInvalidCredentials` | Verifica esuarea cu credentiale invalide |
| 5 | `testGetAllBooks` | Returneaza lista de carti din BookStore |
| 6 | `testGetBookByIsbnInvalid` | Verifica eroarea la ISBN invalid |
| 7 | `testBookHasRequiredFields` | Verifica structura raspunsului pentru o carte |

## Cerinte de instalare

Inainte de a rula proiectul, asigura-te ca ai instalate:

- **Java JDK 25.0.2 (sau versiune compatibila)
- **Maven 3.9.12
- **Google Chrome** Version 148.0.7778.168 (Official Build) (64-bit)
- **Allure CLI** (opțional, doar daca vrei sa rulezi `allure serve` direct)

## Cum se ruleaza proiectul

### 1. Clonare repository

```bash
git clone https://github.com/<mircealivium-mm>/AutomationProiectFinal.git
cd AutomationProiectFinal
```

### 2. Instalare dependinte

```bash
mvn clean install -DskipTests
```

### 3. Rulare toate testele

```bash
mvn clean test
```

Comanda de mai sus va:
- Compila proiectul
- Sterge rezultatele Allure anterioare
- Ruleaza toate testele UI si API definite in `testng.xml`
- Genereaza rezultate Allure in folderul `allure-results/`

### 4. Generare si vizualizare raport Allure

**Optiunea A - Server temporar (recomandat):**

```bash
mvn allure:serve
```

Deschide automat raportul intr-un browser pe un port temporar.

**Optiunea B - Raport static:**

```bash
mvn allure:report
```

Genereaza folderul `allure-report/` cu fisierul `index.html` care poate fi deschis direct in browser sau publicat pe GitHub Pages.

## Ce contine raportul Allure

- **Overview** - dashboard general cu pie chart pass/fail/skipped si procente
- **Categories** - clasificare automata a esecurilor
- **Suites** - testele grupate pe suite-uri TestNG (UI / API)
- **Graphs** - distributia testelor pe severity si durata
- **Timeline** - ordinea executiei in timp
- **Packages** - testele grupate pe package-uri Java
- **Screenshots** - capturi de ecran atasate automat la testele picate

## Pattern-uri si bune practici aplicate

- **Page Object Model (POM)** - separarea logicii de testare de logica de interactiune cu paginile
- **BaseTest** - centralizare setup/teardown WebDriver pentru a evita codul duplicat
- **BasePage** - metode comune pentru toate page-urile (click, setText, getText)
- **PageFactory** - initializare lazy a elementelor cu `@FindBy`
- **Explicit Waits** - `WebDriverWait` pentru sincronizare stabila
- **Screenshot la failure** - prin adnotarea Allure `@Attachment` in `BaseTest`
- **Constants** - URL-uri si date de test definite ca `private static final`

## Bug-uri identificate

| # | Test | Descriere | Severitate |
| --- | --- | --- | --- |
| 1 | `testAddProductToCart` | Selectorul `#add-to-cart` pe pagina de detaliu produs e instabil. Pe SauceDemo, butonul are uneori un ID specific produsului (`add-to-cart-sauce-labs-backpack`). | Medie |

## Autor

Proiect realizat de **Mihailescu Mircea Liviu** ca parte din cursul de Testare Automata.

## Licenta

Proiect realizat in scop educational.
