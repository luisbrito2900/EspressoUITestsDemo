# Android Todo App with Espresso UI Tests

This is a simple Android Todo app built with Kotlin and tested using **Espresso UI tests**.

The purpose of this project is to demonstrate how to:

- Create a functional Todo app in Kotlin.
- Write Espresso integration tests.
- Run UI tests locally or on cloud platforms like **BrowserStack**, **Kobiton**, and **LambdaTest**.

---

## 📱 Features

- Add todo items.
- Update existing items.
- Delete items from the list.
- UI tested with Espresso and JUnit4.

---

## 🧪 Testing

This app uses **Espresso** to test the main UI flows:

- ✅ Add todo item and check it's displayed
- ✏️ Edit item title and verify the update
- ❌ Delete item and confirm it's removed

Tests are located in:

```
app/src/androidTest/java/com/example/importanttodos/UITests.kt
```

---

## ☁️ Run tests on BrowserStack

You can run the tests on BrowserStack App Automate with these steps:

1. Build both APKs:

   - `app-debug.apk`
   - `app-debug-androidTest.apk`

2. Upload them using `curl`:

   ```bash
   curl -u "your-username:your-api-key" \
   -X POST "https://api-cloud.browserstack.com/app-automate/espresso/v2/app" \
   -F "file=@app-debug.apk"
   ```

   ```bash
   curl -u "your-username:your-api-key" \
   -X POST "https://api-cloud.browserstack.com/app-automate/espresso/v2/test-suite" \
   -F "file=@app-debug-androidTest.apk"
   ```

3. Launch the test run:
   ```bash
   curl -u "your-username:your-api-key" \
   -X POST "https://api-cloud.browserstack.com/app-automate/espresso/v2/build" \
   -d '{
     "devices": ["Google Pixel 4-10.0"],
     "app": "bs://your_app_url",
     "testSuite": "bs://your_test_suite_url"
   }' \
   -H "Content-Type: application/json"
   ```

---

## ☁️ Run tests on Kobiton

You can also run the tests on **Kobiton** using a configuration JSON file like this:

**kobiton-config.json**

```json
{
  "device_udid": "your-device-udid",
  "configuration": {
    "sessionName": "Automation test session",
    "deviceName": "Galaxy S24 Ultra",
    "platformVersion": "15",
    "groupId": "<app-group-id>",
    "deviceGroup": "KOBITON",
    "app": "kobiton-store:your-app-id",
    "testRunner": "kobiton-store:your-test-runner-id",
    "continueOnFailure": true,
    "testFramework": "UIAUTOMATOR",
    "retainDurationInSeconds": 0,
    "tests": ["com.example.importanttodos.test"]
  }
}
```

**Run with:**

```bash
curl -u "your-username:your-api-key" \
-X POST "https://api.kobiton.com/hub/session" \
-H "Content-Type: application/json" \
-d @kobiton-config.json
```

---

## ☁️ Run tests on LambdaTest

You can also run the tests on **LambdaTest Real Device Cloud**.

### ✅ Step 1: Upload APKs manually

Go to:  
`Real Device > App Testing > Click on Upload`  
Upload both your:

- `app-debug.apk`
- `app-debug-androidTest.apk`

### ✅ Step 2: Run test via cURL

Once uploaded, use your App ID and TestSuite ID in the following request:

```bash
curl --location --request POST "https://mobile-api.lambdatest.com/framework/v1/espresso/build" \
--header "Authorization: Basic BASIC_AUTH_TOKEN" \
--header "Content-Type: application/json" \
--data-raw '{
  "app": "<your-app-id>",
  "testSuite": "<your-testsuite-id>",
  "device": ["Galaxy S21 5G-12"],
  "queueTimeout": 360,
  "idleTimeout": 150,
  "deviceLogs": true,
  "network": false,
  "build": "EspressoTests",
  "geoLocation": "us"
}'
```

If everything is configured properly, you will receive a response like:

```json
{
  "status": ["Success"],
  "buildId": ["<buil-id>"],
  "message": [""]
}
```

---

## 🚀 Getting Started

To run the app locally:

1. Clone the repo:

   ```bash
   git clone https://github.com/luisbrito2900/EspressoUITestsDemo.git
   ```

2. Open in Android Studio.
3. Run on emulator or device.

To run tests:

```bash
./gradlew connectedAndroidTest
```

---

## 📄 License

This project is licensed under the MIT License.
