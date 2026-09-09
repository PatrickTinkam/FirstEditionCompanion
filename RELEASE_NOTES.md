# First Edition Companion v0.9.6

v0.9.6 establishes a reusable source-details pattern for rules warnings and restrictions in the guided character creator.

## Why? • Source buttons
- Rules-sensitive creator cards now include a small **Why? • Source** button.
- The detail window explains why the current character is seeing the warning/check rather than only repeating the error text.
- The dialog identifies the governing PHB, DMG, or supplied-UA-variant source and includes the relevant section plus a verified page number when one is known.
- Mechanical facts such as allowed classes, minimum scores, age effects, alignment restrictions, and language capacity remain available natively/offline.
- Longer source wording is paraphrased; brief direct excerpts are used only where helpful.

## Warning families covered now
- **Race / class restrictions** — including the core PHB elf class list used by the Elf → Cleric warning.
- **Class ability requirements** — shows selected class requirements, current adjusted scores, and the active failed checks.
- **Age / aging checks** — explains the DMG rule that age adjustments are cumulative and cannot lower abilities below racial/class minimums.
- **Alignment restrictions** — ties the current class/alignment combination back to the PHB alignment/class rule.
- **Language capacity** — explains final-INT capacity, automatic-vs-additional languages, racial limits, and source location.

## Project-wide warning policy
- `DEVELOPMENT.md` now requires future AD&D-mechanical warnings/errors/restrictions to ship with a nearby **Why? / Source Details** affordance whenever the supplied sources support an explanation.
- The same pattern is intended for future weapon proficiency, equipment, spell, magic-item, advancement, and combat validation warnings.
- House rules and supplied-UA-variant mechanics must remain visibly source-labeled instead of being presented as core PHB/DMG rules.

## Existing v0.9.5 behavior retained
- Class selection still separates race/class compatibility from ability-score qualification.
- DMG Methods I and II still use the persistent six-score assignment pool.
- Manual dropdown distribution and **Auto Distribute for Class** remain available.
- Method III remains ability-specific; Method IV remains an in-order complete set.
- Existing saved characters and in-progress creation drafts are not intentionally rewritten by this change.

## Version confirmation
- Main app title: **v0.9.6**
- Character Profiles card: **App Version: v0.9.6**
- Guided creator title: **v0.9.6**

## Updating
v0.9.6 uses the same stable prototype signing key and should install directly over v0.9.5 while preserving app-local data.

## Installation
Download the attached `FirstEditionCompanion-v0.9.6.apk` and install it on Android.
