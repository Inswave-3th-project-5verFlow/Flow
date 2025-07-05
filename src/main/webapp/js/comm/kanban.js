/**
 * 칸반보드 JavaScript
 * 웹스퀘어와 연동하는 간단한 칸반보드
 */

// 전역 변수
var kanbanBoard = null;

/**
 * 칸반보드 초기화 (웹스퀘어에서 호출)
 */
function initKanbanBoard() {
    console.log("=== 칸반보드 JS 초기화 시작 ===");
    
    try {
        // 웹스퀘어 데이터 확인
        if (typeof dlt_kanbanList === 'undefined') {
            console.error("웹스퀘어 데이터가 없습니다.");
            return;
        }
        
        // 칸반보드 인스턴스 생성
        kanbanBoard = new KanbanBoard();
        kanbanBoard.init();
        
        console.log("칸반보드 초기화 완료");
    } catch (error) {
        console.error("칸반보드 초기화 실패:", error);
    }
}

/**
 * 칸반보드 새로고침 (웹스퀘어에서 호출)
 */
function refreshKanban() {
    if (kanbanBoard) {
        kanbanBoard.loadData();
    }
}

/**
 * 칸반보드 클래스
 */
class KanbanBoard {
    constructor() {
        this.draggedCard = null;
    }
    
    // 초기화
    init() {
        console.log("칸반보드 클래스 초기화");
        this.loadData();
        this.setupDragAndDrop();
    }
    
    // 웹스퀘어에서 데이터 로드
    loadData() {
        try {
            console.log("웹스퀘어 데이터 로드 시작");
            
            var rowCount = dlt_kanbanList.getRowCount();
            console.log("데이터 행 수:", rowCount);
            
            if (rowCount === 0) {
                console.warn("데이터가 없습니다.");
                return;
            }
            
            // 기존 카드 모두 제거
            this.clearAllCards();
            
            // 각 데이터를 카드로 변환
            for (var i = 0; i < rowCount; i++) {
                var taskData = dlt_kanbanList.getRowJSON(i);
                this.createAndAppendCard(taskData);
            }
            
            // 카운트 업데이트
            this.updateTaskCounts();
            
            console.log("데이터 로드 완료");
        } catch (error) {
            console.error("데이터 로드 실패:", error);
        }
    }
    
    // 모든 카드 제거
    clearAllCards() {
        var containers = ['todo-cards', 'progress-cards', 'done-cards'];
        containers.forEach(function(containerId) {
            var container = document.getElementById(containerId);
            if (container) {
                container.innerHTML = '';
            }
        });
    }
    
    // 카드 생성 및 추가
    createAndAppendCard(taskData) {
        try {
            var card = this.createCard(taskData);
            var container = this.getContainerByStatus(taskData.taskStatus);
            
            if (container && card) {
                container.appendChild(card);
            }
        } catch (error) {
            console.error("카드 생성 실패:", error, taskData);
        }
    }
    
    // 개별 카드 생성
    createCard(taskData) {
        var card = document.createElement('div');
        card.className = 'kanban-card';
        card.draggable = true;
        card.setAttribute('data-task-id', taskData.taskId);
        card.setAttribute('data-status', taskData.taskStatus);
        
        // 태그 클래스 결정
        var tagClass = this.getTagClass(taskData.tag);
        
        card.innerHTML = `
            <div class="card-title">${taskData.taskName || ''}</div>
            <div class="card-tag ${tagClass}">${taskData.tag || ''}</div>
            <div class="card-footer">
                <div class="card-icons">
                    ${taskData.hasCheck === 'true' ? '<div class="icon check">✓</div>' : ''}
                    ${taskData.hasBlock === 'true' ? '<div class="icon block">⊘</div>' : ''}
                    <div class="icon-group">
                        <div class="icon priority">↑</div>
                        <span>${taskData.priority || 0}</span>
                    </div>
                    ${taskData.commentCount > 0 ? `
                        <div class="icon-group">
                            <div class="icon comment">💬</div>
                            <span>${taskData.commentCount}</span>
                        </div>
                    ` : ''}
                </div>
                <div class="card-assignee">
                    <span class="task-id">${taskData.taskId || ''}</span>
                    <div class="avatar">${taskData.assignee || 'UN'}</div>
                </div>
            </div>
        `;
        
        return card;
    }
    
    // 태그 클래스 결정
    getTagClass(tag) {
        if (!tag) return 'tag-default';
        
        if (tag.includes('SPACE TRAVEL')) return 'tag-space-travel';
        if (tag.includes('MARS OFFICE')) return 'tag-local-mars';
        if (tag.includes('SEESPACEEZ')) return 'tag-seespaceez';
        return 'tag-default';
    }
    
    // 상태별 컨테이너 가져오기
    getContainerByStatus(status) {
        var containerMap = {
            'todo': 'todo-cards',
            'progress': 'progress-cards', 
            'done': 'done-cards'
        };
        
        var containerId = containerMap[status] || 'todo-cards';
        return document.getElementById(containerId);
    }
    
    // 카운트 업데이트
    updateTaskCounts() {
        try {
            var todoCount = document.getElementById('todo-cards').children.length;
            var progressCount = document.getElementById('progress-cards').children.length;
            var doneCount = document.getElementById('done-cards').children.length;
            var totalCount = todoCount + progressCount + doneCount;
            
            // UI 업데이트
            document.getElementById('todo-count').textContent = todoCount;
            document.getElementById('progress-count').textContent = progressCount;
            document.getElementById('done-count').textContent = doneCount;
            
            // 웹스퀘어 총 개수 업데이트
            if (typeof spn_listCnt !== 'undefined') {
                spn_listCnt.setLabel(totalCount);
            }
            
            console.log("카운트 업데이트 - TODO:", todoCount, "PROGRESS:", progressCount, "DONE:", doneCount);
        } catch (error) {
            console.error("카운트 업데이트 실패:", error);
        }
    }
    
    // 드래그앤드롭 설정
    setupDragAndDrop() {
        console.log("드래그앤드롭 이벤트 설정");
        
        var self = this;
        
        // 드래그 시작
        document.addEventListener('dragstart', function(e) {
            if (e.target.classList.contains('kanban-card')) {
                self.draggedCard = e.target;
                e.target.classList.add('dragging');
                e.dataTransfer.effectAllowed = 'move';
                console.log("드래그 시작:", e.target.getAttribute('data-task-id'));
            }
        });
        
        // 드래그 종료
        document.addEventListener('dragend', function(e) {
            if (e.target.classList.contains('kanban-card')) {
                e.target.classList.remove('dragging');
                self.draggedCard = null;
                
                // 모든 drag-over 효과 제거
                document.querySelectorAll('.drag-over').forEach(function(el) {
                    el.classList.remove('drag-over');
                });
            }
        });
        
        // 드래그 오버
        document.addEventListener('dragover', function(e) {
            var dropZone = e.target.closest('.kanban-cards');
            if (dropZone && self.draggedCard) {
                e.preventDefault();
                e.dataTransfer.dropEffect = 'move';
            }
        });
        
        // 드래그 엔터
        document.addEventListener('dragenter', function(e) {
            var dropZone = e.target.closest('.kanban-cards');
            if (dropZone && self.draggedCard) {
                dropZone.classList.add('drag-over');
            }
        });
        
        // 드래그 리브
        document.addEventListener('dragleave', function(e) {
            var dropZone = e.target.closest('.kanban-cards');
            if (dropZone && !dropZone.contains(e.relatedTarget)) {
                dropZone.classList.remove('drag-over');
            }
        });
        
        // 드롭
        document.addEventListener('drop', function(e) {
            e.preventDefault();
            var dropZone = e.target.closest('.kanban-cards');
            
            if (dropZone && self.draggedCard) {
                dropZone.classList.remove('drag-over');
                
                // 카드를 새 위치로 이동
                dropZone.appendChild(self.draggedCard);
                
                // 상태 업데이트
                var newStatus = self.getStatusFromContainer(dropZone.id);
                var taskId = self.draggedCard.getAttribute('data-task-id');
                
                console.log("드롭 완료:", taskId, "->", newStatus);
                
                self.updateCardStatus(taskId, newStatus);
                self.updateTaskCounts();
            }
        });
    }
    
    // 컨테이너 ID로부터 상태 추출
    getStatusFromContainer(containerId) {
        var statusMap = {
            'todo-cards': 'todo',
            'progress-cards': 'progress',
            'done-cards': 'done'
        };
        return statusMap[containerId] || 'todo';
    }
    
    // 카드 상태 업데이트
    updateCardStatus(taskId, newStatus) {
        try {
            console.log("상태 업데이트:", taskId, "->", newStatus);
            
            // 카드 데이터 업데이트
            if (this.draggedCard) {
                this.draggedCard.setAttribute('data-status', newStatus);
            }
            
            // 웹스퀘어 데이터 업데이트
            if (typeof scwin !== 'undefined' && typeof scwin.updateTaskStatus === 'function') {
                scwin.updateTaskStatus(taskId, newStatus);
            } else {
                console.warn("웹스퀘어 업데이트 함수를 찾을 수 없습니다.");
            }
            
        } catch (error) {
            console.error("상태 업데이트 실패:", error);
        }
    }
}