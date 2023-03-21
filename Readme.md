Telegram bot
Example of a working bot

This bot will send you notification with currency exchange rates of the selected currencies from the selected bank at a certain time of the day.

Configuration
Bot settings are stored in the config directory.
The application.properties file stores the application's default settings such as bank, currency and notification time, off by default.
The currency-bot.properties file stores bot settings (name and token).

Run:
Create bot via BotFather (t.me/BotFather)
Add your token and username
Run Application.main()
Start using bot. Your bot language settings is the same as Telegram language.
By default, You will receive exchange rates for US Dollar from Privatbank with notifications switched off.
You can change that in the settings menu.
Bot created using
Gradle
Java 11 LTS
