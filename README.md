# API для сбора статистики VK
## Задание
Сделать набор API-методов для сохранения событий и получения статистики.
Первый метод должен принимать в качестве входных параметров название события и статус пользователя (авторизован или нет). Затем сервер должен добавить вспомогательную информацию и сохранить событие. В качестве хранилища можно использовать mysql/mongo/postgre/sqlite/etc

Второй метод должен позволять фильтровать (по дате и названию события обязательно) и получать агрегированную информацию (одна из трех агрегация за раз) в формате JSON:
- счетчики конкретных событий
- счетчики событий по пользователю (по IP-адресу)
- счетчик событий по статусу пользователя
## Использование
### Запрос данных
Перейдите на сайт https://statistics-api.cfapps.us10-001.hana.ondemand.com

Добавьте в конец ```/statistics?name=EVENT_NAME&date=DATE&filter_by=TYPE```
- **EVENT_NAME** - Название события
- **DATE** - Дата добавления события в формате YYYY-MM-DD
- **TYPE** - Тип агрегации информации из списка: EVENT, USER, STATUS

<details>
  <summary> Пример </summary>
  
  ```https://statistics-api.cfapps.us10-001.hana.ondemand.com/statistics?name=Logged_in&date=2023-05-10&filter_by=user```
  
  Выведет счетчики IP адресов всех пользователей, вызвавших событие 'Logged_in' за 10 мая 2023:
  ```
  {
    "10.154.247.133"  : 1,
    "5.18.216.26" : 3
  }
  ```
</details>

> Примечание: при запуске сервер автоматически сохраняет в базу событие с именем 'Initial_event' и датой инициализации с адреса хоста

Для получения всех событий, находящихся в базе на данный момент, используйте

```https://statistics-api.cfapps.us10-001.hana.ondemand.com/events```
### Сохранение данных
Используйте встроенную утилиту ```curl``` (Доступно для Windows и Linux)

Чтобы добавить данные в формате JSON, введите в командной строке

```curl -X POST -H "Content-Type: application/json" https://statistics-api.cfapps.us10-001.hana.ondemand.com/events -d DATA```

Данные в теле запроса предоставляются в формате:
```
{
  "name" : EVENT_NAME,
  "status" : true/false
}
```
<details>
  <summary> Пример </summary>
  
  ```
  curl -X POST -H "Content-Type: application/json" https://statistics-api.cfapps.us10-001.hana.ondemand.com/events -d "{\"name\" : \"Logged_in\", \"status\" : false}"
  ```
  Добавит в базу событие 'Logged_in' неавторизированного пользователя вместе с текущей датой и адресом отправителя
</details>

### Сторонние платформы
Можно использовать ресурсы для тестирования API (Postman, Insomnia, SOAPI и др.)

Структура запроса аналогична

![](https://github.com/ITerNik/StatisticsAPI/blob/master/images/Postman_query_example.png?raw=true "Пример запроса в Postman")
## Локальная альтернатива
- Используйте ```git clone https://github.com/ITerNik/StatisticsAPI```
- Запустите проект при помощи консоли или IDE (Intellij Idea, Eclipse и др.)
- Перейдите в браузере на страницу http://localhost:8080 и действуйте аналогично подсказкам выше
