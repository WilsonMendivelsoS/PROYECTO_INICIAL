# 🎰 Slot Machine Simulator

> 🚧 **Work in Progress:** This project is actively under development as part of an ongoing course term.

An object-oriented slot machine simulator written in Java. This application models a multi-wheel slot machine mechanism using customizable, color-based shape symbols.

---

## 📌 Context & Background

This simulator is inspired by **Problem I: Slot Machine** from the **2025 ICPC World Finals**. 

While the competitive problem focuses on finding an optimal algorithm to win the jackpot, **this phase focuses entirely on building the simulation engine**, its core domain logic, and its graphical engine interface.

---

## 👥 Authors & Academic Info

- **Wilson Mendivelso** — [@WilsonMendivelsoS](https://github.com/WilsonMendivelsoS)
- **David Garzon** — [@DavidRod0](https://github.com/DavidRod0)

* **Institution:** Escuela Colombiana de Ingeniería Julio Garavito
* **Course:** Object Oriented Development (*DOPO-POOB*)
* **Academic Term:** 2026-2

---

## ✨ System Features

The application provides a full set of functionalities to manage the slot machine lifecycle:

- **Machine Operations:** Initialize a slot machine instance and shut down the engine safely.
- **Dynamic Configuration:** Add or remove individual wheels and modify symbol sets per wheel on the fly.
- **Mechanics & Logic:** Spin the wheels, inspect current visible symbols, and check if the current layout satisfies the jackpot condition.
- **Flexible Execution Modes:** Toggle canvas visibility to run the simulator in either GUI mode or invisible (headless) mode.

---

## 🛠️ Tech Stack & Modeling

| Tool / Standard | Usage |
| :--- | :--- |
| **BlueJ** | Primary IDE and execution environment |
| **Java Shapes Package** | Base library reused and extended for graphical symbol rendering |
| **Astah** | Tool used for UML Class and Sequence diagrams |
| **Javadoc** | Standardized source code documentation |

---

## 📂 Project Structure

```text
├── design/     # UML design models (Astah class & sequence diagrams)
├── docs/       # Retrospective reports, time logs, and references
└── src/        # Java source code implementation
