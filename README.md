# CelebsRating — LAB 7

Application Android développée dans le cadre du cours de
**Programmation Mobile (Android avec Java)**.

## Aperçu de l'application

### Liste des célébrités
<img width="1080" height="2340" alt="Screenshot_20260524_234944_CelebsRating" src="https://github.com/user-attachments/assets/66d997cd-5fe5-4187-b815-4526c0a0c54d" />

### Popup de modification de note
<img width="1080" height="2340" alt="Screenshot_20260524_234955_CelebsRating" src="https://github.com/user-attachments/assets/d9fcfc4d-dfc6-4aea-835f-49eb0b1a010f" />

## Fonctionnalités

- Écran de démarrage animé (WelcomeActivity)
- Liste des célébrités avec photos circulaires et notes (RecyclerView)
- Filtrage dynamique par nom (SearchView)
- Modification de la note via popup (AlertDialog + RatingBar)
- Partage de l'application (ShareCompat)

## Technologies

- Java · Android SDK API 24+
- RecyclerView · CircleImageView · Glide · OkHttp
- Material Design · SearchView · ShareCompat

## Ce que j'ai appris

- Pattern Singleton pour la couche service
- Pattern ViewHolder pour le RecyclerView
- Interface Filterable pour le filtrage dynamique
- AlertDialog personnalisé pour la modification de note
- Animations avec ViewPropertyAnimator
- Chargement d'images réseau avec Glide + OkHttp
