# First Edition Companion v0.9.5

v0.9.5 adds class-aware ability-score distribution on top of the v0.9.4 qualification-order fix.

## Rolled score pool
- **DMG Method I** and **DMG Method II** now create a persistent six-score pool instead of immediately locking values into STR/INT/WIS/DEX/CON/CHA.
- Each ability uses a dropdown containing only score values still available from the shared pool.
- Assigning a value consumes one copy from the pool; changing or clearing that slot returns its previous value.
- Duplicate numerical rolls are tracked as separate copies rather than collapsing into one value.
- The rolled pool is stored in the character draft so Back navigation, fold/unfold, rotation, or draft resume do not lose the original six rolls.

## Auto Distribute for Class
- After Methods I or II roll a pool, the player can choose **Auto Distribute for Class** or assign every score manually.
- Auto Distribution first tries to satisfy all encoded class minimums after racial modifiers, then favors prime/principal abilities.
- Recognized multiclass/dual-class strings are evaluated as a combined requirement set so one class is not optimized at the expense of another.
- Auto Distribution never locks the scores; the player can still change every assignment afterward with the dropdowns.
- If the rolled pool cannot satisfy all encoded requirements, the app uses the closest class-focused arrangement and tells the player that the character still does not qualify.

## Method integrity
- **Method III** results stay tied to the individual abilities they were rolled for.
- **Method IV** keeps the chosen complete set in STR/INT/WIS/DEX/CON/CHA order.
- **Manual Entry** remains fully editable.

## Qualification timing carried forward from v0.9.4
- The Class step checks race/class compatibility only and shows ability minimums as advance guidance.
- Actual ability-score qualification happens after rolling/distribution and racial adjustments.
- Age applies its modifiers afterward and performs another final class-requirement check.
- Manual typing continues to refresh the visible qualification result immediately.

## Version confirmation
- Main app title: **v0.9.5**
- Character Profiles card: **App Version: v0.9.5**
- Guided creator title: **v0.9.5**

## Updating
v0.9.5 uses the same stable prototype signing key and should install directly over v0.9.4 while preserving app-local data. Existing saved characters are not rewritten by this change.

## Installation
Download the attached `FirstEditionCompanion-v0.9.5.apk` and install it on Android.
