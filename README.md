#  MERCURIO - JavaFX & Gradle App (with BootstrapFX)

## 📌 Description
Desktop application developed in **Java** using **JavaFX** and **Gradle**, with a modern UI based on **BootstrapFX**.  
The project follows an **MVVM/MVC** architecture, is ready for **automated testing**, **CI/CD**, and can generate native installers via **jpackage/jlink**.

---

## 🧩 Main Technologies
- **Java 17 / 21 LTS**  
- **Gradle**  
- **JavaFX (controls, fxml, web)**  
- **BootstrapFX**  
- **JUnit 5**  
- *(Optional)* SQLite / JDBC  
- *(Optional)* TestFX, Checkstyle, SpotBugs  
- **GitHub Actions** for CI/CD  

---

## ✅ Requirements
- Install **JDK 17 or 21**  
- Use the included **Gradle Wrapper** (`gradlew`)  
- **Git** configured on your system  
- Internet connection to download dependencies  

---

## 🗂️ Project Structure
```

src/main/java/
com.yourapp/
app/
view/
controller/ or viewmodel/
model/
service/
repo/
util/
src/main/resources/
fxml/
css/
i18n/
db/
web/

````

---

## 🚀 Quick Start
Run the app:
```bash
./gradlew run
````

Run tests:

```bash
./gradlew test
```

Build artifacts:

```bash
./gradlew build
```

---

## 🧭 Roadmap by Sprints

### 🥇 Sprint 1 — Functional Skeleton

* [ ] Create main scene with base layout
* [ ] Integrate BootstrapFX and custom CSS
* [ ] Define architecture (MVC/MVVM) and packages
* [ ] Implement simple CRUD workflow with validation
* [ ] Add basic persistence (SQLite/JDBC or in-memory)
* [ ] Set up internationalization (i18n)
* [ ] Add unit tests for services and logic
* [ ] Document project conventions

---

### 🥈 Sprint 2 — UX, Data & Quality

* [ ] Add paginated/searchable table view
* [ ] Improve form validation and error handling
* [ ] Add user preferences (light/dark theme, language)
* [ ] Complete internationalization (EN/ES)
* [ ] Implement configurable logging (levels, file output)
* [ ] Set up Checkstyle and SpotBugs
* [ ] Configure GitHub Actions pipeline

---

### 🥉 Sprint 3 — Distribution & Maintenance

* [ ] Native packaging with **jpackage/jlink**
* [ ] Configuration file (`application.properties` / `.env`)
* [ ] Semantic versioning and `CHANGELOG`
* [ ] Issue and PR templates for GitHub
* [ ] GitHub Projects board and public roadmap
* [ ] Contribution guide and security policy
* [ ] UI testing (optional) with TestFX

---

## 🧱 Conventions & Best Practices

* Layered architecture (**model / service / repo / controller / viewmodel**)
* Keep business logic out of controllers
* Centralized validation and i18n messages
* Never block the JavaFX thread (use `Task`, `Service`)
* Consistent visual theme (BootstrapFX + `app.css`)
* Proper code documentation and comments

---

## 🌐 Internationalization (i18n)

* Translation files:

    * `i18n/messages_en.properties`
    * `i18n/messages_es.properties`
* Avoid hardcoded text in the code
* Localize date, number, and currency formats

---

## 🎨 Styles & Themes

* Base theme: **BootstrapFX**
* Custom styles in `css/app.css`
* Light/dark mode toggle based on user preferences

---

## 🗄️ Persistence

* Default: **embedded SQLite**
* `Repository` interface with a concrete implementation
* Database initialization scripts (`resources/db/init.sql`)
* Optional seed/test data

---

## 🔎 Quality & CI/CD

* **JUnit 5** for unit testing
* **Checkstyle / SpotBugs** for static analysis
* **GitHub Actions** workflow:

    1. Checkout repository
    2. Setup Java (Temurin 17/21)
    3. Build and run tests
    4. Publish artifacts

---

## 🔐 Security

* Do not commit secrets or credentials
* Use environment variables or GitHub Secrets
* Validate all user input
* Enable Dependabot for dependency updates

---

## 🔧 Configuration

* `application.properties` for general settings
* `.env` for secrets and credentials
* Environment profiles: `dev`, `test`, `prod`

---

## 📦 Distribution

* **jlink** to create a minimal runtime
* **jpackage** to generate native installers
* Publish signed release artifacts on GitHub

---

## 🧪 Testing

* Unit tests for services and viewmodels
* Integration tests for persistence and controllers
* *(Optional)* UI tests using TestFX
* Use fixtures and mock data for consistency

---

## 🔀 Git Workflow

* Branches:

    * `main` → stable
    * `develop` → integration
    * `feature/*` → new features
    * `fix/*` → bug fixes
* Commit style: **Conventional Commits**
* PRs must be reviewed before merging into `main`

---

## 📝 Project Management

* Use GitHub Issues with labels (`bug`, `feature`, `enhancement`)
* Milestones per sprint
* Kanban board with **GitHub Projects**
* Keep roadmap up to date in this README

---

## 🧰 Troubleshooting

| Issue              | Solution                                     |
| ------------------ | -------------------------------------------- |
| App won’t start    | Verify JDK version and Gradle setup          |
| Styles not applied | Check BootstrapFX and CSS loading paths      |
| UI freezing        | Avoid blocking the JavaFX Application Thread |
| DB not found       | Check file path and permissions for SQLite   |

---

## 🤝 Contributing

1. Fork this repository
2. Create a branch `feature/...`
3. Implement your changes and tests
4. Commit using Conventional Commits
5. Open a Pull Request to `develop`
6. Wait for review and merge

---

## 📄 License

Distributed under the **MIT License** (or preferred license).
Include a `LICENSE` file in the root directory.

---

## 🗒️ CHANGELOG

Follow **Semantic Versioning (SemVer)**
Categories:

* **Added** → New features
* **Changed** → Behavior changes
* **Fixed** → Bug fixes
* **Removed** → Deprecated or removed items

---

## 📚 FAQ

**Can I use another database?**
Yes, by implementing a new `Repository` class.

**Do I need WebView?**
No, only if you want to embed HTML views with Bootstrap.

**Can I switch from MVVM to MVC?**
Yes, as long as the layer separation remains clear.

---

## ✅ Global Checklist

* [ ] Base architecture and structure defined
* [ ] BootstrapFX styles applied
* [ ] CRUD workflow implemented
* [ ] Persistence (SQLite or memory) added
* [ ] Full i18n (EN/ES) implemented
* [ ] Unit tests and quality checks running
* [ ] CI/CD (GitHub Actions) operational
* [ ] Logging configured
* [ ] Native packaging ready
* [ ] Versioning and CHANGELOG set up
* [ ] CONTRIBUTING guide and LICENSE file added
* [ ] Active roadmap and GitHub boards

---

## 🧾 Credits

Developed by the JavaFX development team.
Maintained by contributors and the open-source community.

```
```
