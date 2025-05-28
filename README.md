### CI with Gradle: 
![Build Status](https://img.shields.io/github/actions/workflow/status/dmitry-mak/testauto-diplom/gradle.yml?branch=main)


## Автоматизированное тестирование сервиса онлайн-покупки билетов

### Необходимое программное обеспечение: 
1. IntelliJ Idea
1. JDK 11- рекомендуемая версия комплекта разработчика.
1. Gradle
1. Git/GitHub
1. DockerDesktop
1. GoogleChrome browser

### Порядок действий для запуска SUT и авто тестов:
1. Скопировать репозиторий на компьютер.
- для этого выбрать директорию, куда будет помещен проект
- кликнуть правой кнопкой мыши по директории и выбрать пункт Git Bash Here
- в открывшемся терминале ввести команду для копирования: git clone git@github.com:dmitry-mak/testauto-diplom.git
2. Открыть скопированный проект в IntelliJ Idea
2. Запустить Docker Desktop
3. В терминале IntelliJ Idea ввести команду "docker-compose up" чтобы запустить контейнер с системой, необходимой для работы тестируемого приложения
4. В новом окне терминала IntelliJ Idea запустить тестируемое приложение командой  "java -jar./artifacts/aqa-shop.jar"

### Запуск автоматизированных тестов
1. Для запуска автоматизированных тестов, в новой вкладке терминала IntelliJ Idea ввести команду "./gradlew clean test"
2. Для генерации отчетов Allure по результатам тестирования, в новой терминале IntelliJ Idea ввести команду "./gradlew allureReport"
- Отчет будет доступен по адресу testAuto_Diplom\build\reports\allure-report\allureReport
- Файл index.html , содержащий отчеты открывается любым интернет-браузером



