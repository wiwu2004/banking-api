# 🎵 Lyrics API

A backend API for processing song lyrics, translating verses, and providing **on-demand audio pronunciation** for each original verse. The project is designed as a learning-focused, portfolio-ready application showcasing clean architecture, language processing, and text-to-speech integration.

---

## 🚀 Features

- ✅ Split lyrics into individual verses
- ✅ Translate verses between multiple languages (EN, ES, PT)
- ✅ Generate **audio pronunciation for the original verse** using Text-to-Speech
- ✅ Audio generated **on-demand** (no file storage)
- ✅ Clean separation between domain, services, and external clients
- ✅ Reactive-compatible (Spring WebFlux style, even when blocking integration is used)

---


## 🌍 Supported Languages

| Enum | Language |
|----|---------|
| EN | English |
| ES | Spanish |
| PT | Portuguese |

---

## 📦 API Endpoints

### 🔹 Process Lyrics

**POST** `/lyrics/process`

#### Request Body
```json
{
  "text": "Hello my friend
How are you today?",
  "sourceLang": "EN",
  "targetLang": "PT"
}
```

#### Response Example
```json
{
  "verses": [
    {
      "origin": "Hello my friend",
      "translated": "Olá meu amigo",
      "audioUrl": "/tts/audio?text=Hello%20my%20friend&lang=EN"
    }
  ]
}
```

---

### 🔊 Text To Speech (Audio)

**GET** `/tts/audio`

#### Query Parameters

- `text` → text that will be spoken
- `lang` → language (`EN`, `ES`, `PT`)

#### Example
```
/tts/audio?text=Hello%20my%20friend&lang=EN
```

Returns:
- `Content-Type: audio/mpeg`
- Streamed MP3 audio playable directly in browsers

---

## 🧪 Testing with Postman

```json
{
  "text": "Good morning
This is a test",
  "sourceLang": "EN",
  "targetLang": "PT"
}
```

> ⚠️ Enum values are **case-sensitive** (`EN`, not `en`).

---

## 💡 Design Decisions

- 🎧 Audio is generated **lazily** to reduce load and storage
- 🧩 Translation and TTS are kept decoupled
- 🧠 Optimized for language-learning use cases
- 📁 No filesystem usage, fully stateless

---

## 🛠 Tech Stack

- Java 25
- Spring Boot 
- Spring Web MVC / WebFlux style
- Reactor (Mono / Flux)
- Google Translate TTS (unofficial)
- Langbly Translation API

---

## ✅ Status

✔ Core features completed  
✔ Fully functional MVP  
✔ Portfolio-ready

---

**Developed: Willian Wu**
