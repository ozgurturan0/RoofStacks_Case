# 📘 README (Setup Instructions)

## Remwaste\_Case (Test Project) & DevConnector\_2.0 (App Project)

### 🔧 Prerequisites

- Java 1.8
- Node.js v16.20.2
- npm v8.19.4
- MongoDB Atlas or local MongoDB
- Chrome browser + matching ChromeDriver
- 

---

### 🚀 Setup Instructions

#### 1. Clone Repositories

```bash
git clone https://github.com/ozgurturan0/Remwaste_Case.git
git clone https://github.com/ozgurturan0/devconnector_2.0.git
```

#### 2. Setup & Run the DevConnector App

```bash
cd devconnector_2.0
npm install
cd client
npm install
cd ..
npm run dev
```

- App should now be running on [http://localhost:5000](http://localhost:5000)

#### 3. Setup & Run the Test Suite


- For UI tests -> `testNG_UI.xml`
- For API tests -> `testNG_API.xml`

#### 4. View Results

- Test report: `Remwaste_Case/src/test/report/*.html`
- Screenshots (on failure): `test-output/screenshots/`

---

# 📄 Test Plan / Strategy

## 1. Scope & Objective

Automated testing for the DevConnector\_2.0 MERN app using Remwaste_Case. Tests verify critical user workflows and UI functionality.

## 2. Test Coverage

- **Authentication:**
  - Valid login
  - Invalid login
  - Registration
  - Token verification
- **Profiles:**
  - Create/edit/view profiles
- **Posts:**
  - Add, delete, like/unlike
  - Comment add/delete
- **Error Handling:**
  - Form validation
  - Unauthorized access
- **UI:**
  - Page loads, navigation, messages

## 3. Tools Used

| Tool               | Purpose                                       |
| ------------------ | --------------------------------------------- |
| Selenium WebDriver | UI automation (Java)                          |
| TestNG             | Test framework & lifecycle management         |
| ExtentReports      | HTML report generation with screenshots       |
| ConfigReader       | Externalize configuration                     |
| ReusableMethods    | Utilities (screenshot, JS click, waits, etc.) |

## 4. How to Run the Tests

1. Start DevConnector app on `localhost:5000`
2. Navigate to test repo `Remwaste_Case`
3. Run tests via Maven:



## 5. Assumptions & Limitations

- App must be running and accessible at localhost:5000
- Only Chrome browser is supported in current config
- MongoDB and GitHub API credentials must be valid
- No mobile/responsive testing
- Performance or load testing is not included

---

## ✅ Summary

| Item           | Description                               |
| -------------- | ----------------------------------------- |
| App Under Test | DevConnector\_2.0 (MERN stack app)        |
| Test Repo      | Remwaste\_Case                            |
| Coverage       | Auth, profiles, posts, error messages, UI |
| Tech Stack     | Selenium, Java, TestNG, ExtentReports     |
| Execution Time | \~1–2 mins                                |
| Output         | HTML reports & screenshots                |

