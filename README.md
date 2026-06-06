# eGovFrame Skills

한국 **전자정부 표준프레임워크(eGovFrame)** 규약에 맞춰 코드를 생성하도록 코딩 에이전트를 확장하는
[Agent Skills](https://code.claude.com/docs/en/skills) 모음입니다. Agent Skills는 오픈 표준이라
**Claude Code, Codex(OpenAI), Cursor, Gemini CLI** 등에서 동일한 `SKILL.md`로 동작합니다.

가만 두면 코딩 에이전트는 일반적인 Spring 관례로 코드를 만들어 전자정부 적용기준을 위반합니다
(ServiceImpl이 `EgovAbstractServiceImpl`을 상속하지 않는 등). 이 스킬들은 그 간극을 메워
적용점검(호환성 확인) 통과를 목표로 합니다.

## 포함된 스킬

| 스킬 | 설명 |
|---|---|
| `egovframe-rest` | 전자정부 표준프레임워크 **REST API 백엔드** 개발. 3.x(전통 Spring MVC + XML)와 4.x(Spring Boot) 모두 지원. SKILL.md가 먼저 버전을 판별한 뒤 해당 버전 규약·템플릿만 로드합니다. |

> 앞으로 화면(JSP/React), 배치, 공통컴포넌트 등 스킬을 같은 레포에 추가할 수 있습니다.

## 설치

`npx skills` (Vercel Labs) 를 사용하면 한 줄로 설치됩니다. 설치된 코딩 에이전트를 자동 감지합니다.

```bash
# 레포의 스킬 목록 보기
npx skills add <your-name>/egovframe-skills --list

# 특정 스킬 설치
npx skills add <your-name>/egovframe-skills --skill egovframe-rest

# 특정 에이전트 지정 (예: codex, claude-code)
npx skills add <your-name>/egovframe-skills --skill egovframe-rest -a claude-code -a codex

# 전부 설치
npx skills add <your-name>/egovframe-skills --all
```

수동 설치도 가능합니다. 원하는 스킬 폴더(예: `egovframe-rest/`)를 에이전트의 스킬 디렉터리
(`.claude/skills/`, `.codex/skills/`, `.agents/skills/` 등)에 복사하세요.

## 사용 예

설치 후 자연어로 요청하면 스킬이 자동 발동됩니다.

```
"전자정부 프로젝트에 Sample 게시판 CRUD REST API 만들어줘"
"이 ServiceImpl이 표준프레임워크 적용기준에 맞는지 검토해줘"
```

## 구조

```
egovframe-rest/
├── SKILL.md                       # 버전 판별 + 작업 절차 (항상 로드)
├── references/
│   ├── architecture-rules.md      # 적용기준(아키텍처 규칙) + 점검 체크리스트 (공통)
│   ├── naming-conventions.md      # 패키지/클래스/파일 네이밍 (공통)
│   ├── v3-rest.md                 # 3.x 상세
│   └── v4-rest.md                 # 4.x 상세
└── templates/
    ├── v3/                        # 3.x 코드 골격
    └── v4/                        # 4.x 코드 골격
```

## 주의

전자정부 표준프레임워크는 버전이 계속 갱신됩니다. 템플릿의 라이브러리 버전(`3.x`, `4.x` 등)은
플레이스홀더이며, 정확한 버전·적용기준 세부는 공식 포털
[egovframe.go.kr](https://www.egovframe.go.kr) 의 최신 "적용기준/호환성 가이드"로 검증하세요.

## 라이선스

MIT — 자유롭게 사용·수정·배포할 수 있습니다. `LICENSE` 참고.

## 기여

이슈와 PR 환영합니다. 새 스킬을 추가할 때는 위 구조(SKILL.md + references + templates)를 따르고,
적용기준에 근거한 규칙을 명시해 주세요.
