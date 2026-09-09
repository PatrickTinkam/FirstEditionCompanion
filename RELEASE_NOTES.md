# First Edition Companion v0.9.4

v0.9.4 separates race/class compatibility from ability-score qualification in the guided character creator and fixes related validation UX.

## Class selection
- The Class step now checks **race compatibility only**.
- Race-compatible classes are labeled clearly as race-compatible while their ability qualification remains pending.
- Race-restricted classes remain visible for learning and **Class Details**, but are labeled as a true race restriction rather than a failed ability check.
- The screen explicitly explains that ability rolls cannot override a core race/class restriction.
- Exact class requirements remain visible before rolling so players know what minimum scores they need to aim for.
- A new draft defaults to the first race-compatible class instead of accidentally landing on an unavailable first item in the list.

## Ability-score qualification
- Actual class minimum-score enforcement happens after ability rolling/distribution and after racial adjustments are applied.
- The Ability Scores step now has a dedicated **Class Qualification Check** showing the selected class and its requirements.
- Continue remains disabled until all six scores are present and the current race/class/ability requirements pass.
- The Rules Check now refreshes live while manually editing or rearranging scores instead of displaying stale results.
- Age adjustments still trigger a second final-score qualification check on the Age step, so later modifiers cannot silently invalidate a class requirement.

## Related rule-order review
- Race/class legality remains separate from ability eligibility, matching core 1e structure.
- Ability-dependent nonhuman class level limits are treated as advancement limits, not as a reason to reject an otherwise legal level-1 class choice during class selection.
- Existing drafts, saved characters, spell tracks, gear, and profile data are not rewritten by this change.

## Version confirmation
- Main app title: **v0.9.4**
- Character Profiles card: **App Version: v0.9.4**
- Guided creator title: **v0.9.4**

## Updating
v0.9.4 uses the same stable prototype signing key and should install directly over v0.9.3 while preserving app-local data. Export Saves before major updates is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.9.4.apk` and install it on Android.
