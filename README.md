# Framing Lib

[![License](https://img.shields.io/badge/License-MIT%20License-green?style=for-the-badge)](https://raw.githubusercontent.com/PlagueTR/FramingLib/refs/heads/main/LICENSE)
![Environment](https://img.shields.io/badge/Environment-Client-blue?style=for-the-badge)
[![Ko-Fi](https://img.shields.io/badge/BUY%20ME%20A%20COFFEE-limegreen?style=for-the-badge&logo=kofi&logoColor=white&logoSize=auto)](https://ko-fi.com/plaguetr)

A lightweight, client-sided configuration screen library for creating interactive, draggable HUD layout screens!

## 📖 What is this mod?

This is a client-side configuration screen library for creating draggable HUD layout screens.

It allows developers to create configuration screens with interactive, draggable elements to make positioning their HUD/GUI elements easier for the player.<br>
This is not a complete configuration API, it is simply for creating configuration screens.

Basically, this is for other mods that rely on it to work.

Here are some visuals to help show what this does:

![Features Showcase](https://raw.githubusercontent.com/PlagueTR/FramingLib/main/showcase/features.gif)

![Demo Screen](https://raw.githubusercontent.com/PlagueTR/FramingLib/main/showcase/demo_screen.gif)

A more practical example, moving chat around:

![Example Move Chat](https://raw.githubusercontent.com/PlagueTR/FramingLib/main/showcase/example_chat.gif)

## ✅ Features

- **Multi-Loader Support:** Fully compatible with Fabric, Quilt, Forge and NeoForge
- **Intuitive for Players:** Allows repositioning HUD/GUI elements by simply dragging them around with the mouse
- **Developer-Friendly API:** Uses a simple builder pattern, making integration easy for any HUD/GUI mod
- **Dynamic Alignment:** Anchors HUD/GUI elements to nine screen positions automatically, with optional snapping
- **Smart Recalculation:** Recalculates HUD/GUI locations when GUI scale or window size changes
- **Lightweight & Performant:** Caches and only recalculates positions when necessary to save frames
- **Client-Side Only:** Installed only on the client, no server-side footprint or installation required
- **Extensively Customizable:** Supports custom colors, icons, and even using your own custom rendering functions

> **⚠️** To remain flexible, this library **does not** automatically save or load layout data to the disk. Developers are responsible for serializing the layout object (into JSON/YAML/INI/your preferred config format) and deserializing it when the game initializes.

## 📖 Usage

### 📖 For Players

> **⚠️️** This is a library mod, and does nothing on its own.

Put the jar file in your mods folder and you're good to go!

For [Fabric] and [Quilt] version, you probably also need to put [ModMenu] in your mods folder.

### 📖 For Developers

Developer documentation can be accessed [here][Developer Documentation]

## 🚀 Supported Versions

| Minecraft |  Fabric   |   Quilt   |   Forge   | NeoForge |
|:---------:|:---------:|:---------:|:---------:|:--------:|
| **26.2**  |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  26.1.2   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  26.1.1   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
| **26.1**  |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.11  |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.10  |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.9   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.8   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.7   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.6   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.5   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.4   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.3   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.2   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.21.1   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
| **1.21**  |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.20.6   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.20.5   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.20.4   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.20.3   |    ❌     |    ❌     |    ❌     |    ❌    |
|  1.20.2   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
|  1.20.1   |    ⏳     |    ⏳     |    ⏳     |    ⏳    |
| **1.20**  |    ⏳     |    ⏳     |    ⏳     |    ❌    |
|  1.19.4   |    ⏳     |    ⏳     |    ⏳     |    ❌    |
|  1.19.3   |    ⏳     |    ⏳     |    ⏳     |    ❌    |
|  1.19.2   |    ⏳     |    ⏳     |    ⏳     |    ❌    |
|  1.19.1   |    ⏳     |    ⏳     |    ⏳     |    ❌    |
| **1.19**  |    ⏳     |    ⏳     |    ⏳     |    ❌    |
|  1.18.2   |    ⏳     |    ⏳     |    ⏳     |    ❌    |
|  1.18.1   |    ⏳     |    ⏳     |    ⏳     |    ❌    |
| **1.18**  | ✅(1.0.4) | ✅(1.0.4) | ✅(1.0.4) |    ❌    |
|  1.17.1   | ✅(1.0.3) | ✅(1.0.3) | ✅(1.0.3) |    ❌    |
| **1.17**  |    ❌     |    ❌     |    ❌     |    ❌    |
|  1.16.5   | ✅(1.0.2) | ✅(1.0.2) | ✅(1.0.2) |    ❌    |

✅ - Supported ⏳ - Planned ⏳ - Not supported

Notes:
- Latest mod version is written in parentheses

[Fabric]: https://fabricmc.net
[Quilt]: https://quiltmc.org
[Forge]: https://minecraftforge.net
[NeoForge]: https://neoforged.net
[ModMenu]: https://modrinth.com/mod/modmenu "ModMenu Modrinth page"
[Developer Documentation]: https://plaguetr.github.io/FramingLib/
