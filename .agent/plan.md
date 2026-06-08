# Project Plan

背屏激发 (Back Screen Activation) - An Android app for Xiaomi 17 Pro to control back screen brightness via root shell commands. It toggles between locking the brightness to maximum and restoring system control. State detection will be based on the file permissions of /sys/class/backlight/panel1-backlight/brightness.

## Project Brief

# Project Brief: 背屏激发 (Back Screen Activation)

A utility application designed specifically for the Xiaomi
 17 Pro to provide granular control over the device's secondary (back) screen brightness. By leveraging root-
level shell commands, the app allows users to override system defaults and lock the back screen to peak brightness or return control to the OS
.

### Features
1.  **Root Access Validation**: Automatically checks for and verifies root permissions on launch to ensure the app can execute
 necessary shell commands.
2.  **One-Tap Brightness Lock**: A centralized toggle to force the Xiaomi 17 Pro'
s back screen to its maximum brightness level instantly.
3.  **Restore System Control**: A single action to release
 the brightness lock, allowing the Android system to resume automatic brightness management for the back display.
4.  **Persistent Status Monitoring**: A real-time indicator on the dashboard showing whether the brightness is currently locked or under system control.
5.
  **State Persistence**: Remembers the user's last selection (Locked vs. System) across app restarts using lightweight
 local storage.

### High-Level Tech Stack
-   **Kotlin**: The core language for robust and expressive Android
 development.
-   **Jetpack Compose**: The modern toolkit for building a reactive, Material 3-compliant user
 interface.
-   **Jetpack Navigation 3**: For state-driven navigation logic and efficient UI state management.
-   
**Compose Material Adaptive**: To ensure the UI layout is responsive and adapts perfectly to the device's unique form factor.
-   **Kotlin
 Coroutines**: For non-blocking execution of root-level shell commands, ensuring a smooth UI performance.
-   **
DataStore (Preferences)**: For persistent storage of the user's brightness lock settings.

## Implementation Steps

### Task_1_Core_Logic: Implement root check utility and shell execution helper to manage /sys/class/backlight/panel1-backlight/brightness. Include logic to change file permissions (chmod) and write brightness values.
- **Status:** IN_PROGRESS
- **Acceptance Criteria:**
  - Root access verification works
  - Shell command wrapper can execute chmod and echo commands
  - Utility can read file permissions to detect current state

### Task_2_State_and_Persistence: Setup DataStore for persistent settings and a ViewModel to manage the app state, including monitoring the brightness lock status and executing toggles via the core utilities.
- **Status:** PENDING
- **Acceptance Criteria:**
  - DataStore correctly saves and retrieves the lock state
  - ViewModel reacts to state changes and triggers shell commands
  - State detection logic correctly identifies if the system or the app has control

### Task_3_UI_Development: Build the Material 3 dashboard using Jetpack Compose with a vibrant, energetic color scheme. Implement the brightness toggle, real-time status indicator, and full edge-to-edge display.
- **Status:** PENDING
- **Acceptance Criteria:**
  - UI matches Material 3 guidelines and energetic aesthetic
  - Toggle button correctly triggers the brightness lock/restore
  - Status indicator reflects the real-time state of the back screen
  - Edge-to-edge display is functional

### Task_4_Final_Polish_and_Verification: Create an adaptive app icon, refine the theme with Material Color Utilities, and perform final verification of the application stability and root functionality.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Adaptive app icon created and displayed
  - Color scheme follows Material 3 for light and dark modes
  - Application builds and runs without crashes
  - Brightness lock functionality verified as working via root shell
  - All existing tests pass

